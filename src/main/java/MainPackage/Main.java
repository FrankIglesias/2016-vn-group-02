package MainPackage;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Main {

	public static void main(String[] args) {
		Spark.port(10020);
		
		  System.out.println("Iniciando servidor");
		  
		  HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		  MainController home = new MainController();
		  
		  Spark.staticFileLocation("/templates");
		  Spark.get("/", home::mostrar, engine);
		  Spark.get("/administrador",home::mostrarAdmin, engine);
		  Spark.get("/usuario", home::mostrarUser, engine);
		  Spark.get("/admin_terminales", home::mostrarTerminales, engine);
		  Spark.get("/admin_pois", home::mostrarPois, engine);
		  Spark.get("/admin_consultas", home::mostrarConsultas, engine);
		  Spark.put("/admin_pois", home::filtrarNombreTipoPois, engine);
		  Spark.post("/admin_pois",home::imprimiQueLlegueAca,engine);
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