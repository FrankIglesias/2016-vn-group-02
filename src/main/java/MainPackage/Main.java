package MainPackage;

import java.io.IOException;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Main {

	public static void main(String[] args) {
		Integer puerto = 10024;
		Spark.port(puerto);
		
		  System.out.println("Iniciando servidor");
		  
		  HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		  MainController home = new MainController();
		  
		  Spark.staticFileLocation("/templates");
		  Spark.get("/", home::mostrar, engine);
		  Spark.get("/administrador",home::mostrarAdmin, engine);
		  Spark.get("/usuario", home::mostrarUser, engine);
		  Spark.get("/admin_terminales", home::mostrarTerminales, engine);
		  Spark.get("/editar_terminal", home::mostrarEditarTerminal, engine);
		  Spark.get("/admin_acciones", home::mostrarAdminAcciones, engine);
		  Spark.get("/admin_pois", home::mostrarPois, engine);
		  Spark.get("/admin_consultas", home::mostrarConsultas, engine);
		  Spark.get("/buscar_pois", home::filtrarNombreTipoPois, engine);
		  Spark.get("/usuarioBusqueda", home::buscarPois, engine);
		  Spark.get("/ver_mas", home::verMas,engine);
		  Spark.get("/editar_poi", home::editarPoi,engine);
		  Spark.get("/buscar_terminales",home::buscarTerminal,engine);
		  Spark.delete("/borrar_poi/:id", home::borrarPoi,engine);
		  Runtime rt = Runtime.getRuntime();
		  try {
			Process pr = rt.exec("\"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe\" http:\\\\localhost:"+puerto.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	}
}