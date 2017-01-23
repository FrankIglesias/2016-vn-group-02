package MainPackage;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import DesignDreamTeamLocation.Domicilio;
import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamLocation.Localidad;
import DesignDreamTeamTime.Feriado;
import DesignDreamTeamTime.GestorIntervalos;
import DesignDreamTeamTime.HorarioYDia;
import DesignDreamTeamTime.IntervaloHorario;
import HashMapeameEsta.HashMapeameEsta;
import Repositorios.Buscador;
import Repositorios.RepoPOIs;
import Repositorios.RepoTerminales;
import Repositorios.Terminal;
import TypePois.Banco;
import TypePois.CGP;
import TypePois.Colectivo;
import TypePois.Local;
import TypePois.POI;
import TypePois.Rubro;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MainController implements WithGlobalEntityManager, TransactionalOps {
	private String nombreUsuario;
	private Terminal terminal;

	HashMapeameEsta agenda = new HashMapeameEsta();
	POI poiACambiar;

	public ModelAndView mostrar(Request request, Response response) {
		System.out.println("Mostrar Main");
		return new ModelAndView(null, "Frank.hbs");
	}

	public ModelAndView mostrarAdmin(Request request, Response response) {
		System.out.println("Mostrar Panel Admin");
		return new ModelAndView(null, "Administrador.hbs");
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
			viewModel.put("nombreUsuario", nombreUsuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(viewModel, "masDetalleUsuario.hbs");
	}



	public ModelAndView nuevoPoi(Request request, Response response) {
		System.out.println("Agregando poi " + request.queryParams("nombre"));
		POI poiAPersistir = new Local();
		switch (request.queryParams("tipoFiltro")) {
		case "local":
			poiAPersistir = new Local();
			poiAPersistir.setRubro(new Rubro(request.queryParams("rubro"), 0));
			break;
		case "banco":
			poiAPersistir = new Banco();
			break;
		case "cgp":
			poiAPersistir = new CGP();
			break;
		case "colectivo":
			poiAPersistir = new Colectivo();
			poiAPersistir.setLinea(request.queryParams("linea"));
			break;
		}

		Domicilio unaDomi = new Domicilio(request.queryParams("calle_principal"), request.queryParams("entre_calles"),
				request.queryParams("altura"), request.queryParams("piso"), request.queryParams("unidad"),
				request.queryParams("codigo_postal"), Integer.parseInt(request.queryParams("comuna")));
		Localidad unaLoca = new Localidad(request.queryParams("ciudad"), request.queryParams("provincia"),
				request.queryParams("pais"));
		Geolocalizacion unaGeo = new Geolocalizacion(Double.parseDouble(request.queryParams("latitud")),
				Double.parseDouble(request.queryParams("lng")), unaDomi, unaLoca);

		List<Feriado> feriadosAPersistir = new ArrayList<Feriado>();

		List<String> feriados;
		if (request.queryParamsValues("feriados") != null) {
			feriados = new ArrayList<String>(Arrays.asList(request.queryParamsValues("feriados")));
			feriados.forEach(feri -> feriadosAPersistir
					.add(new Feriado(Integer.valueOf(feri.split("/")[1]), Integer.valueOf(feri.split("/")[0]), null)));
		}

		poiAPersistir.setGeo(unaGeo);
		poiAPersistir.setNombre(request.queryParams("nombre"));

		poiAPersistir.addPalabrasClaves(request.queryParams("tipoFiltro"));
		poiAPersistir.addPalabrasClaves(poiAPersistir.getNombre());
		poiAPersistir.addPalabrasClaves(request.queryParams("calle_principal"));
		poiAPersistir.addPalabrasClaves(request.queryParams("ciudad"));
		poiAPersistir.addPalabrasClaves(request.queryParams("provincia"));
		poiAPersistir.addPalabrasClaves(request.queryParams("pais"));

		poiAPersistir.setFeriados(feriadosAPersistir);

		List<String> dias;
		if (request.queryParamsValues("dias") != null) {
			dias = new ArrayList<String>(Arrays.asList(request.queryParamsValues("dias")));
			dias.stream().forEach(unDia -> guardarHorarioDelDia(unDia, request));
		}

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


	
	public ModelAndView mostrarUser(Request request, Response response) {
		System.out.println("Se loggeo el usuario " + request.queryParams("nombreFiltro"));
		HashMap<String, Object> viewModel = new HashMap<>();
		nombreUsuario = request.queryParams("nombreFiltro");
		try {
			terminal = RepoTerminales.getInstance().buscameUnaTerminal(nombreUsuario);
			if (terminal == null) {
				viewModel.put("mensaje", "Usuario y/o contraseña incorrectos");
				return new ModelAndView(viewModel, "Frank.hbs");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		viewModel.put("nombreUsuario", nombreUsuario);
		return new ModelAndView(viewModel, "usuario.hbs");
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
			viewModel.put("nombreUsuario", nombreUsuario);
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
			viewModel.put("nombreUsuario", nombreUsuario);
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
			poiACambiar = RepoPOIs.getInstance().obtenerDeHibernate(Integer.parseInt(nombreFiltro));

			viewModel.put("poi", poiACambiar);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(viewModel, "editar_poi.hbs");
	}

	public ModelAndView cambiarPoi(Request request, Response response) {
		System.out.println("Actualizando poi...");
		nuevoPoi(request, response);

		try {
			RepoPOIs.getInstance().borrarDeHibernateSegunId(poiACambiar.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// RepoPOIs.getInstance().borrarDeMongo(poiACambiar);

		return new ModelAndView(null, "admin_pois.hbs");

	}
}