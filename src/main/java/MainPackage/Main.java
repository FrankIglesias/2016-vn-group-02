package MainPackage;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import java.io.IOException;

import Controllers.ControllerRepoBusquedas;
import Controllers.ControllerRepoPoi;
import Controllers.ControllerRepoTerminales;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Main {

	public static void main(String[] args) {
		Integer puerto = 10024;
		port(puerto);

		System.out.println("Iniciando servidor");
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		MainController home = new MainController();
		ControllerRepoTerminales controllerTerminales = ControllerRepoTerminales.getInstance();
		ControllerRepoPoi controllerPois = ControllerRepoPoi.getInstance();
		ControllerRepoBusquedas controllerBusquedas = ControllerRepoBusquedas.getInstance();

		staticFileLocation("/templates");
		get("/", home::mostrar, engine);

		get("/administrador", home::mostrarAdmin, engine);
		get("/usuario", home::mostrarUser, engine);

		get("/admin_terminales", controllerTerminales::mostrarTerminales, engine);
		get("/buscar_terminales", controllerTerminales::buscarTerminal, engine);
		get("/editar_terminal", controllerTerminales::mostrarEditarTerminal, engine);
		get("/modificar_terminal", controllerTerminales::modificarTerminal, engine);
		get("/admin_acciones", controllerTerminales::mostrarAdminAcciones, engine);
		get("/nueva_terminal", controllerTerminales::nuevaTerminal, engine);
		get("/agregar_terminal", controllerTerminales::agregarTerminal, engine);
		delete("/borrar_terminal", controllerTerminales::borrarTerminal);
		get("/agregar_accion", controllerTerminales::nuevaAccion, engine);

		get("/admin_pois", controllerPois::mostrarPois, engine);
		get("/buscar_pois", controllerPois::buscarPoisAdmin, engine);
		get("/editar_poi", home::editarPoi, engine);
		get("/cambiar_poi", home::cambiarPoi, engine);
		delete("/borrar_poi", controllerPois::borrarPoi);
		get("/masDetallePoi", controllerPois::masDetalleAdministrador, engine);
		get("/nuevo_poi", controllerPois::agregarPoi, engine);
		get("/agregar_poi", home::nuevoPoi, engine);
		get("/usuarioBusqueda", home::buscarPois, engine);
		get("/masDetallePoiUser", home::masDetalleUsuario, engine);
		get("/ver_mas", home::verMas, engine);

		get("/admin_consultas", controllerBusquedas::mostrarConsultas, engine);
		get("/buscar_consultas", controllerBusquedas::buscarBusquedas, engine);
		get("/ver_pois_consultas", controllerBusquedas::verPoisConsultas, engine);

		get("*", (request, response) -> {
			return new ModelAndView(null, "notFound.hbs");
		}, engine);
		Runtime rt = Runtime.getRuntime();

		try {
			rt.exec("\"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe\" http:\\\\localhost:"
					+ puerto.toString());
		} catch (IOException e) {
		}

	}

}