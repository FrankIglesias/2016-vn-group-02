package MainPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Repositorios.Buscador;
import Repositorios.RepoPOIs;
import Repositorios.Terminal;
import TypePois.POI;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MainController {
	private String nombreUsuario;

	public ModelAndView mostrar(Request request, Response response) {
		System.out.println("Mostrar Main");
		return new ModelAndView(null, "home.hbs");
	}
	public ModelAndView borrarPoi(Request request, Response response) {
		System.out.println("Se quiso borrar un poi");
		return new ModelAndView(null, "admin_pois.hbs");
	}
	public ModelAndView mostrarEditarTerminal(Request request, Response response) {
		System.out.println("Editar Terminal");
		return new ModelAndView(null, "editar_terminal.hbs");
	}

	public ModelAndView mostrarAdminAcciones(Request request, Response response) {
		System.out.println("Administrar acciones por terminal");
		return new ModelAndView(null, "admin_acciones.hbs");
	}

	public ModelAndView mostrarEditarPoi(Request request, Response response) {
		System.out.println("Editar POI");
		return new ModelAndView(null, "editar_poi.hbs");
	}

	public ModelAndView mostrarAdmin(Request request, Response response) {
		System.out.println("Mostrar Panel Admin");
		return new ModelAndView(null, "Administrador.hbs");
	}

	public ModelAndView mostrarUser(Request request, Response response) {

		nombreUsuario = request.queryParams("nombreFiltro");
		System.out.println("Mostrar Usuario " + nombreUsuario);
		return new ModelAndView(null, "usuario.hbs");
	}

	public ModelAndView mostrarTerminales(Request request, Response response) {
		System.out.println("Mostrar Admin Terminales");
		return new ModelAndView(null, "admin_terminales.hbs");
	}

	public ModelAndView mostrarConsultas(Request request, Response response) {
		System.out.println("mostrarConsultas");
		return new ModelAndView(null, "admin_consultas.hbs");
	}

	public ModelAndView mostrarPois(Request request, Response response) {
		return new ModelAndView(null, "admin_pois.hbs");
	}

	public ModelAndView filtrarNombreTipoPois(Request request, Response response) {
		System.out.println("FiltrarNombrePois");

		HashMap<String, Object> viewModel = new HashMap<>();
		String nombreFiltro = request.queryParams("nombreFiltro");
		String tipoFiltro = request.queryParams("tipoFiltro");

		List<POI> pois = new Controllers.ControllerRepoPoi().listarPOIsParaAdmin(nombreFiltro, tipoFiltro);
		viewModel.put("listadoPOIs", pois);

		List<String> coordenadas = new ArrayList<String>();
		pois.forEach(unPoi -> coordenadas
				.add("{lat:" + unPoi.getPoint().getLatitud() + ", lng:" + unPoi.getPoint().getLongitud() + "}"));
		viewModel.put("latitudes", coordenadas);
		return new ModelAndView(viewModel, "admin_pois.hbs");
	}

	public ModelAndView busquedaUsuario(Request request, Response response) {
		nombreUsuario = request.queryParams("nombreFiltro");
		System.out.println("busquedaUsuario" + nombreUsuario);
		return new ModelAndView(null, "usuario.hbs");
	}

	public ModelAndView verMas(Request request, Response response) {
		System.out.println("Ver mas");
		HashMap<String, Object> viewModel = new HashMap<>();
		String nombreFiltro = request.queryParams("nombreFiltro");
		List<POI> pois = new Buscador().buscarPoisHibernate(nombreFiltro, new Terminal(nombreUsuario));
		viewModel.put("listadoPOIs", pois);
		List<String> coordenadas = new ArrayList<String>();
		pois.forEach(unPoi -> coordenadas
				.add("{lat:" + unPoi.getPoint().getLatitud() + ", lng:" + unPoi.getPoint().getLongitud() + "}"));
		viewModel.put("latitudes", coordenadas);
		return new ModelAndView(viewModel, "usuario.hbs");
	}

	public ModelAndView buscarPois(Request request, Response response) {
		System.out.println("busquedaUsuario");

		HashMap<String, Object> viewModel = new HashMap<>();
		String nombreFiltro = request.queryParams("nombreFiltro");
		List<POI> pois = new Buscador().buscarPoisMongo(nombreFiltro, new Terminal(nombreUsuario));
		viewModel.put("listadoPOIs", pois);
		List<String> coordenadas = new ArrayList<String>();
		pois.forEach(unPoi -> coordenadas
				.add("{lat:" + unPoi.getPoint().getLatitud() + ", lng:" + unPoi.getPoint().getLongitud() + "}"));
		viewModel.put("latitudes", coordenadas);
		return new ModelAndView(viewModel, "usuario.hbs");
	}

	public ModelAndView editarPoi(Request request, Response response) {
		System.out.println("busquedaUsuario");
		HashMap<String, Object> viewModel = new HashMap<>();
		String nombreFiltro = request.queryParams("id");

		POI pois = RepoPOIs.getInstance().obtenerDeHibernate(Integer.parseInt(nombreFiltro));
		viewModel.put("poi", pois);
		List<String> coordenadas = new ArrayList<String>();
		coordenadas.add("{lat:" + pois.getPoint().getLatitud() + ", lng:" + pois.getPoint().getLongitud() + "}");
		viewModel.put("latitudes", coordenadas);
		return new ModelAndView(viewModel, "editar_poi.hbs");
	}

}