package MainPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import TypePois.POI;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MainController {
	public ModelAndView mostrar(Request request, Response response) {
		System.out.println("Mostrar Main");
		return new ModelAndView(null, "home.hbs");
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
		System.out.println("Mostrar Usuario");
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

	public ModelAndView filtrarNombreTipoPois(Request request, Response response) {
		System.out.println("FiltrarNombrePois");

		HashMap<String, Object> viewModel = new HashMap<>();
		String nombreFiltro = request.queryParams("nombreFiltro");
		String tipoFiltro = request.queryParams("tipoFiltro");

		System.out.println("Alla");
		List<POI> pois = new Controllers.ControllerRepoPoi().listarPOIsParaAdmin(nombreFiltro, tipoFiltro);
		viewModel.put("listadoPOIs", pois);

		List<String> coordenadas = new ArrayList<String>();
		pois.forEach(unPoi -> coordenadas
				.add("{lat:" + unPoi.getPoint().getLatitud() + ", lng:" + unPoi.getPoint().getLongitud() + "}"));
		viewModel.put("latitudes", coordenadas);
		return new ModelAndView(viewModel, "admin_pois.hbs");
	}

	public ModelAndView busquedaUsuario(Request request, Response response) {
		System.out.println("busquedaUsuario");
		return new ModelAndView(null, "usuario.hbs");
	}

}