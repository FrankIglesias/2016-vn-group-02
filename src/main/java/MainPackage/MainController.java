package MainPackage;

import java.util.HashMap;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Controllers.ControllerRepoBusquedas;
import Controllers.ControllerRepoPoi;
import Controllers.ControllerRepoTerminales;
import Repositorios.Buscador;
import Repositorios.Busqueda;
import Repositorios.RepoPOIs;
import Repositorios.RepoTerminales;
import Repositorios.Terminal;
import TypePois.POI;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MainController implements WithGlobalEntityManager, TransactionalOps {
	private String nombreUsuario;
	private Terminal terminal;

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
			withTransaction(() -> {
				Terminal terminalABorrar = RepoTerminales.getInstance()
						.buscameUnaTerminal(request.queryParams("nombre"));
				System.out.println("Terminal encontrada");
				ControllerRepoTerminales.getInstance().eliminarUnaTerminal(terminalABorrar);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.redirect("/admin_terminales");
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

	public ModelAndView buscarTerminal(Request request, Response response) {
		System.out.println("Buscar Terminal");
		HashMap<String, Object> viewModel = new HashMap<>();
		try {
			String nombre = request.queryParams("nombre");
			int comuna;
			if (request.queryParams("comuna").endsWith("0")) {
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

	public ModelAndView mostrarAdminAcciones(Request request, Response response) {
		System.out.println("Administrar acciones por terminal");
		return new ModelAndView(null, "admin_acciones.hbs");
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
			List<POI> poisDeLaBusqueda = ControllerRepoBusquedas.getInstance().buscarUnaBusquedaPorId(idBusqueda)
					.getPuntosBuscados();
			viewModel.put("listadoPOIs", poisDeLaBusqueda);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(viewModel, "ver_pois_consultas.hbs");
	}

	public ModelAndView mostrarUser(Request request, Response response) {
		System.out.println("Se loggeo el usuario " + request.queryParams("nombreFiltro"));
		nombreUsuario = request.queryParams("nombreFiltro");
		String latitud = request.queryParams("latitud");
		String longitud = request.queryParams("longitud");
		System.out.println("Latitud: " + latitud + " Longitud: " + longitud);
		terminal = RepoTerminales.getInstance().buscameUnaTerminal(nombreUsuario);
		if (terminal == null) {
			try {
				terminal = ControllerRepoTerminales.getInstance().agregarUnaTerminal(nombreUsuario, 1, latitud,
						longitud);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
		System.out.println("FiltrarNombrePois");
		HashMap<String, Object> viewModel = new HashMap<>();
		String nombreFiltro = request.queryParams("nombreFiltro");
		String tipoFiltro = request.queryParams("tipoFiltro");
		try {
			List<POI> pois = new Controllers.ControllerRepoPoi().listarPOIsParaAdmin(nombreFiltro, tipoFiltro);
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