package MainPackage;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Main {

	public static void main(String[] args) {
		Spark.port(1515);
		
		  System.out.println("Iniciando servidor");
		  
		  HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		  MainController home = new MainController();
		  
		  Spark.staticFileLocation("/templates");
		  
		  Spark.get("/", home::mostrar, engine);
		  Spark.get("/administrador.hbs",home::mostrarAdmin, engine);
		  Spark.get("/usuario.hbs", home::mostrarUser, engine);
		  Spark.get("/admin_terminales.hbs", home::mostrarTerminales, engine);
		  Spark.get("/admin_pois.hbs", home::mostrarPois, engine);
		  Spark.get("/admin_consultas.hbs", home::mostrarConsultas, engine);
		  Spark.put("/admin_pois.hbs", home::filtrarNombreTipoPois, engine);
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