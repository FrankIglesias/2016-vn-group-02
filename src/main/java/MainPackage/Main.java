package MainPackage;

import static spark.Spark.*;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import spark.template.handlebars.HandlebarsTemplateEngine;

public class Main {

	
	public static void main(String[] args) {
		System.out.println("Iniciando servidor");
		
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
	    MainController home = new MainController();
	    
	    staticFileLocation("/templates");
		
        get("/hello", home::mostrar, engine);
    }
}
	
	/*public static void main(String[] args) {
	    System.out.println("Iniciando servidor");
	    HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
	    MainController home = new MainController();
	    port(5050);

	   // staticFileLocation("/public");

	    get("/", home::mostrar, engine);
	    get("/index.html", (request, response) -> {
	      response.redirect("/");
	      return null;
	    });

	}*/

//}
