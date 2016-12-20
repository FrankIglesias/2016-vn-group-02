package MainPackage;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import TypePois.POI;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MainController {
	public ModelAndView mostrar(Request request, Response response) {
		
		return new ModelAndView(null, "home.hbs");
	}

	public ModelAndView mostrarAdmin(Request request, Response response) {
		
		return new ModelAndView(null, "Administrador.hbs");
	}

	public ModelAndView mostrarUser(Request request, Response response) {
		return new ModelAndView(null, "usuario.hbs");
	}

	public ModelAndView mostrarTerminales(Request request, Response response) {
		return new ModelAndView(null, "admin_terminales.hbs");
	}

	public ModelAndView mostrarPois(Request request, Response response) {
		
		HashMap<String, Object> viewModel = new HashMap<>();
		String nombreFiltro = request.queryParams("nombreFiltro");
		String tipoFiltro = request.queryParams("tipoFiltro");

		if (!(Objects.isNull(nombreFiltro) || nombreFiltro.isEmpty() || tipoFiltro.equals("vacio"))) {
			System.out.println("Alla");
			List<POI> pois = new Controllers.ControllerRepoPoi().listarPOIsParaAdmin(nombreFiltro, tipoFiltro);
			viewModel.put("listadoPOIs", pois);
		}
		return new ModelAndView(null, "admin_pois.hbs");
	}

	public ModelAndView mostrarConsultas(Request request, Response response) {
		return new ModelAndView(null, "admin_consultas.hbs");
	}

	public ModelAndView filtrarNombreTipoPois(Request request, Response response) {
		HashMap<String, Object> viewModel = new HashMap<>();
		String nombreFiltro = request.queryParams("nombreFiltro");
		String tipoFiltro = request.queryParams("tipoFiltro");

		if (!(Objects.isNull(nombreFiltro) || nombreFiltro.isEmpty() || tipoFiltro.equals("vacio"))) {
			List<POI> pois = new Controllers.ControllerRepoPoi().listarPOIsParaAdmin(nombreFiltro, tipoFiltro);
			viewModel.put("listadoPOIs", pois);
		}
		return new ModelAndView(null, "admin_pois.hbs");
	}
	
	public ModelAndView imprimiQueLlegueAca(Request r,Response res){
		return new ModelAndView(null, "admin_pois.hbs");
	}
	public ModelAndView busquedaUsuario(Request request,Response response){
		return new ModelAndView(null, "usuario.hbs");
	}

}
