package MainPackage;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Controllers.ControllerRepoBusquedas;
import Controllers.ControllerRepoPoi;
import Controllers.ControllerRepoTerminales;
import DesignDreamTeamLocation.Domicilio;
import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamLocation.Localidad;
import DesignDreamTeamTime.GestorIntervalos;
import DesignDreamTeamTime.HorarioYDia;
import DesignDreamTeamTime.IntervaloHorario;
import HashMapeameEsta.HashMapeameEsta;
import Repositorios.Buscador;
import Repositorios.Busqueda;
import Repositorios.RepoPOIs;
import Repositorios.RepoTerminales;
import Repositorios.Terminal;
import TypePois.Banco;
import TypePois.CGP;
import TypePois.Colectivo;
import TypePois.Local;
import TypePois.POI;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MainController implements WithGlobalEntityManager, TransactionalOps {
	private String nombreUsuario;
	private Terminal terminal;
	HashMapeameEsta agenda = new HashMapeameEsta();

	public ModelAndView mostrar(Request request, Response response) {
		System.out.println("Mostrar Main");
		return new ModelAndView(null, "Frank.hbs");
	}

	public ModelAndView mostrarAdmin(Request request, Response response) {
		System.out.println("Mostrar Panel Admin");
		return new ModelAndView(null, "Administrador.hbs");
	}

	public Void borrarTerminal(Request request, Response response) {
		System.out.println("Se quiso borrar terminal nombre: " + request.queryParams("nombre"));
		try {
		
				Terminal terminalABorrar = RepoTerminales.getInstance()
						.buscameUnaTerminal(request.queryParams("nombre"));
				ControllerRepoTerminales.getInstance().eliminarUnaTerminal(terminalABorrar);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.redirect("admin_terminales");
		return null;
	}
	public ModelAndView agregarPoi(Request request, Response response) {
		System.out.println("Nuevo Poi");
		return new ModelAndView(null, "agregar_poi.hbs");
	}

	public Void borrarPoi(Request request, Response response) {
		System.out.println("Se quiso borrar un poi" + request.queryParams("id"));
		try {
			ControllerRepoPoi.getInstance().borrarUnPOIporId(request.queryParams("id"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ModelAndView masDetalleUsuario(Request request, Response response) {
		System.out.println("Mostrar mas detalles");
		String idpoi = request.queryParams("id").trim();
		HashMap<String, Object> viewModel = new HashMap<>();
		try {
			POI unpoi = RepoPOIs.getInstance().obtenerDeHibernate(Integer.parseInt(idpoi));

			if (unpoi.getClass().toString().endsWith("TypePois.CGP")) {
				viewModel.put("servi", ((TypePois.CGP) unpoi).getServicios());
			}
			viewModel.put("POI", unpoi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(viewModel, "masDetalleUsuario.hbs");
	}

	public ModelAndView modificarTerminal(Request request, Response response) {
		System.out.println("Guardar una terminal");
		String nombre = request.queryParams("nombre");
		String nombreViejo = request.queryParams("nombreViejo");
		String comuna = request.queryParams("comuna");
		try {
			Terminal nuevaTerminal;
			Terminal terminalAModificar = RepoTerminales.getInstance().buscameUnaTerminal(nombreViejo);
			RepoTerminales.getInstance().eliminarUnaTerminal(terminalAModificar);
			nuevaTerminal = new Terminal(nombre, Integer.parseInt(comuna));
			terminalAModificar.getListaDeAcciones().forEach(a -> nuevaTerminal.addAccion(a));
			RepoTerminales.getInstance().add(nuevaTerminal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(null, "admin_terminales.hbs");
	}

	public ModelAndView masDetalleAdministrador(Request request, Response response) {
		System.out.println("Mostrar mas detalles");
		String idpoi = request.queryParams("id");
		POI unpoi = RepoPOIs.getInstance().obtenerDeHibernate(Integer.parseInt(idpoi));
		HashMap<String, Object> viewModel = new HashMap<>();
		if (unpoi.getClass().toString().endsWith("TypePois.CGP")) {
			viewModel.put("servi", ((TypePois.CGP) unpoi).getServicios());
		}
		viewModel.put("POI", unpoi);
		return new ModelAndView(viewModel, "masDetalleAdministrador.hbs");
	}

	public ModelAndView nuevoPoi(Request request, Response response) {
		System.out.println("Agregando poi " + request.queryParams("nombre"));
		POI poiAPersistir = new Local();
		switch (request.queryParams("tipoFiltro")) {
		case "local":
			poiAPersistir = new Local();
		case "banco":
			poiAPersistir = new Banco();
		case "cgp":
			poiAPersistir = new CGP();
		case "colectivo":
			poiAPersistir = new Colectivo();
		}

		Domicilio unaDomi = new Domicilio(request.queryParams("calle_principal"), request.queryParams("entre_calles"),
				request.queryParams("altura"), request.queryParams("piso"), request.queryParams("unidad"),
				request.queryParams("codigo_postal"), Integer.parseInt(request.queryParams("comuna")));
		Localidad unaLoca = new Localidad(request.queryParams("ciudad"), request.queryParams("provincia"),
				request.queryParams("pais"));
		Geolocalizacion unaGeo = new Geolocalizacion(Double.parseDouble(request.queryParams("latitud")),
				Double.parseDouble(request.queryParams("lng")), unaDomi, unaLoca);

		poiAPersistir.setGeo(unaGeo);
		poiAPersistir.setNombre(request.queryParams("nombre"));

		poiAPersistir.addPalabrasClaves(request.queryParams("tipoFiltro"));
		poiAPersistir.addPalabrasClaves(poiAPersistir.getNombre());
		poiAPersistir.addPalabrasClaves(request.queryParams("calle_principal"));
		poiAPersistir.addPalabrasClaves(request.queryParams("ciudad"));
		poiAPersistir.addPalabrasClaves(request.queryParams("provincia"));
		poiAPersistir.addPalabrasClaves(request.queryParams("pais"));

		List<String> dias = new ArrayList<String>(Arrays.asList(request.queryParamsValues("dias")));

		dias.stream().forEach(unDia -> guardarHorarioDelDia(unDia, request));
		HorarioYDia horario = new HorarioYDia(agenda);
		poiAPersistir.setHorario(horario);

		try {
			RepoPOIs.getInstance().persistirEnHibernate(poiAPersistir);
		} catch (Exception e) {
			e.printStackTrace();
		}

		agenda = new HashMapeameEsta(); // limpio agenda global para proximos
										// pois
		return new ModelAndView(null, "admin_pois.hbs");
	}

	DayOfWeek dayOfWeek(String unDia) {
		switch (unDia) {
		case "lunes":
			return DayOfWeek.MONDAY;
		case "martes":
			return DayOfWeek.TUESDAY;
		case "miercoles":
			return DayOfWeek.WEDNESDAY;
		case "jueves":
			return DayOfWeek.THURSDAY;
		case "viernes":
			return DayOfWeek.FRIDAY;
		case "sabado":
			return DayOfWeek.SATURDAY;
		case "domingo":
			return DayOfWeek.SUNDAY;
		}

		return null;
	}

	void guardarHorarioDelDia(String unDia, Request request) {

		GestorIntervalos gestor = new GestorIntervalos();
		List<IntervaloHorario> intervalos = new ArrayList<IntervaloHorario>();
		String desde = request.queryParams("desde_" + unDia);
		String hasta = request.queryParams("hasta_" + unDia);

		IntervaloHorario intervalo = new IntervaloHorario(
				LocalDateTime.now().withHour(Integer.parseInt(desde.split(":")[0]))
						.withMinute(Integer.parseInt(desde.split(":")[1])),
				LocalDateTime.now().withHour(Integer.parseInt(hasta.split(":")[0]))
						.withMinute(Integer.parseInt(hasta.split(":")[1])));

		intervalos.add(intervalo);
		gestor.setIntervalosHorarios(intervalos);
		agenda.put(dayOfWeek(unDia), gestor);

	}

	public ModelAndView buscarTerminal(Request request, Response response) {
		System.out.println("Buscar Terminal");
		HashMap<String, Object> viewModel = new HashMap<>();
		String nombre;
		try {
			if (request.queryParams("nombre").equals("")) {
				nombre = "";
			} else {
				nombre = request.queryParams("nombre");
			}
			int comuna;
			if (request.queryParams("comuna").equals("")) {
				comuna = -1;
			} else {
				comuna = Integer.parseInt(request.queryParams("comuna"));
			}
			List<Terminal> terminales = ControllerRepoTerminales.getInstance().listarTerminales(nombre, comuna);
			viewModel.put("terminales", terminales);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return new ModelAndView(viewModel, "admin_terminales.hbs");
	}

	public ModelAndView mostrarEditarTerminal(Request request, Response response) {
		System.out.println("Editar Terminal");
		String nombreFiltro = request.queryParams("nombre");
		HashMap<String, Object> viewModel = new HashMap<>();
		try {
			List<Terminal> terminales = ControllerRepoTerminales.getInstance().listarTerminales(nombreFiltro, -1);
			viewModel.put("terminales", terminales.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(viewModel, "editar_terminal.hbs");
	}

	public ModelAndView agregarTerminal(Request request, Response response) {
		System.out.println("Se agrego terminal");
		ControllerRepoTerminales.getInstance().agregarUnaTerminal(request.queryParams("nombre"),
				Integer.parseInt(request.queryParams("comuna")), "0", "0");
		return new ModelAndView(null, "admin_terminales.hbs");
	}

	public ModelAndView nuevaTerminal(Request request, Response response) {
		System.out.println("Nueva terminal");
		return new ModelAndView(null, "nueva_terminal.hbs");
	}

	public ModelAndView mostrarAdminAcciones(Request request, Response response) {
		System.out.println("Administrar acciones por terminal");
		String nombre = request.queryParams("nombre");
		HashMap<String, Object> viewModel = new HashMap<>();
		try {
			Terminal terminal = RepoTerminales.getInstance().buscameUnaTerminal(nombre);
			viewModel.put("acciones", terminal.getListaDeAcciones());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView(viewModel, "admin_acciones.hbs");
	}

	public ModelAndView mostrarEditarPoi(Request request, Response response) {
		System.out.println("Editar POI");
		return new ModelAndView(null, "editar_poi.hbs");
	}

	public ModelAndView verPoisConsultas(Request request, Response response) {
		System.out.println("Ver pois de una consulta");
		String idBusqueda = request.queryParams("id");
		HashMap<String, Object> viewModel = new HashMap<>();
		try {
			List<Integer> idsPOIs = ControllerRepoBusquedas.getInstance().buscarUnaBusquedaPorId(idBusqueda)
					.getPuntosBuscados();
			List<POI> poisDeLaBusqueda = new ArrayList<POI>();

			idsPOIs.forEach(
					unID -> poisDeLaBusqueda.add(RepoPOIs.getInstance().obtenerDeHibernateSegunId(unID.toString())));
			viewModel.put("listadoPOIs", poisDeLaBusqueda);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(viewModel, "ver_pois_consultas.hbs");
	}

	public ModelAndView mostrarUser(Request request, Response response) {
		System.out.println("Se loggeo el usuario " + request.queryParams("nombreFiltro"));
		nombreUsuario = request.queryParams("nombreFiltro");
		try {
			terminal = RepoTerminales.getInstance().buscameUnaTerminal(nombreUsuario);
			if (terminal == null) {
				return new ModelAndView(null, "Frank.hbs");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView(null, "usuario.hbs");
	}

	public ModelAndView mostrarTerminales(Request request, Response response) {
		System.out.println("Mostrar Admin Terminales");
		return new ModelAndView(null, "admin_terminales.hbs");
	}

	public ModelAndView mostrarConsultas(Request request, Response response) {
		System.out.println("Mostrar Consultas");
		return new ModelAndView(null, "admin_consultas.hbs");
	}

	public ModelAndView buscarBusquedas(Request request, Response response) {
		System.out.println("Buscando busquedas");
		HashMap<String, Object> viewModel = new HashMap<>();
		try {
			List<Busqueda> busquedas = ControllerRepoBusquedas.getInstance().listarBusquedas(
					request.queryParams("nombre"), request.queryParams("desde"), request.queryParams("hasta"),
					request.queryParams("cantidad"));

			viewModel.put("consultas", busquedas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(viewModel, "admin_consultas.hbs");
	}

	public ModelAndView mostrarPois(Request request, Response response) {
		System.out.println("Ver pois sin listado");
		return new ModelAndView(null, "admin_pois.hbs");
	}

	public ModelAndView buscarPoisAdmin(Request request, Response response) {
		System.out.println("Busqueda de POIs por admin");
		HashMap<String, Object> viewModel = new HashMap<>();
		String nombreFiltro = request.queryParams("nombreFiltro");
		String tipoFiltro = request.queryParams("tipoFiltro");
		try {
			List<POI> pois = ControllerRepoPoi.getInstance().listarPOIsParaAdmin(nombreFiltro, tipoFiltro);
			viewModel.put("listadoPOIs", pois);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return new ModelAndView(viewModel, "admin_pois.hbs");
	}

	public ModelAndView busquedaUsuario(Request request, Response response) {
		nombreUsuario = request.queryParams("nombreFiltro");
		System.out.println("Busqueda de " + nombreUsuario);
		return new ModelAndView(null, "usuario.hbs");
	}

	public ModelAndView verMas(Request request, Response response) {
		System.out.println("Ver mas (Busqueda desde hibernate)");
		HashMap<String, Object> viewModel = new HashMap<>();
		String nombreFiltro = request.queryParams("nombreFiltro");
		try {
			List<POI> pois = new Buscador().buscarPoisHibernate(nombreFiltro, terminal);
			viewModel.put("listadoPOIs", pois);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(viewModel, "usuario.hbs");
	}

	public ModelAndView buscarPois(Request request, Response response) {
		System.out.println("Busqueda desde MONGO");
		HashMap<String, Object> viewModel = new HashMap<>();
		String nombreFiltro = request.queryParams("nombreFiltro");
		try {
			List<POI> pois = new Buscador().buscarPoisMongo(nombreFiltro, terminal);
			viewModel.put("listadoPOIs", pois);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView(viewModel, "usuario.hbs");
	}

	public ModelAndView editarPoi(Request request, Response response) {
		System.out.println("Editar POI " + request.queryParams("id"));
		HashMap<String, Object> viewModel = new HashMap<>();
		String nombreFiltro = request.queryParams("id");
		try {
			POI pois = RepoPOIs.getInstance().obtenerDeHibernate(Integer.parseInt(nombreFiltro));
			viewModel.put("poi", pois);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(viewModel, "editar_poi.hbs");
	}
}