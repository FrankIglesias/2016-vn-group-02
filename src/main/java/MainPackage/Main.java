package MainPackage;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Main {

	public static void main(String[] args) {
		Spark.port(7776);
		
		  System.out.println("Iniciando servidor");
		  
		  HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		  MainController home = new MainController();
		  
		  Spark.staticFileLocation("/templates");
		  
		  Spark.get("/", home::mostrar, engine);
		  Spark.get("/administrador.html",home::mostrarAdmin, engine);
		  Spark.get("/usuario.html", home::mostrarUser, engine);
		  Spark.get("/admin_terminales.html", home::mostrarTerminales, engine);
		  Spark.get("/admin_pois.html", home::mostrarPois, engine);
		  Spark.get("/admin_consultas.html", home::mostrarConsultas, engine);
		  Spark.put("/admin_pois.html", home::filtrarNombreTipoPois, engine);
		 	}
}

/*
 * public static void main(String[] args) { System.out.println(
 * "Iniciando servidor"); HandlebarsTemplateEngine engine = new
 * HandlebarsTemplateEngine(); MainController home = new MainController();
 * port(5050); // staticFileLocation("/public"); get("/", home::mostrar,
 * engine); get("/index.html", (request, response) -> { response.redirect("/");
 * return null; }); }
 */

// }