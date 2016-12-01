import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import spark.template.handlebars.HandlebarsTemplateEngine;

public class Main {

	public static void main(String[] args) {
	    System.out.println("Iniciando servidor");
	    HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
	    MainController home = new MainController();
	    port(8080);

	    staticFileLocation("/public");

	    get("/", home::mostrar, engine);
	    get("/index.html", (request, response) -> {
	      response.redirect("/");
	      return null;
	    });

	}

}
