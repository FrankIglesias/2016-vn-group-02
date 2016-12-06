package MainPackage;

import Repositorios.Buscador;
import Repositorios.Terminal;

public class Main {

	public static void main(String[] args) {

		System.out.println("Iniciando servidor");

		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		MainController home = new MainController();

		staticFileLocation("/templates");

		get("/", home::mostrar, engine);
		get("/administrador.html", home::mostrarAdmin, engine);
		get("/usuario.html", home::mostrarUser, engine);
		get("/admin_terminales.html", home::mostrarTerminales, engine);
		get("/admin_pois.html", home::mostrarPois, engine);
		get("/admin_consultas.html", home::mostrarConsultas, engine);

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