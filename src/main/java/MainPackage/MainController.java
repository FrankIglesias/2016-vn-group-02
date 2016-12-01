package MainPackage;

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
		    return new ModelAndView(null, "admin_pois.hbs");
		  }
	  
	  public ModelAndView mostrarConsultas(Request request, Response response) {
		    return new ModelAndView(null, "admin_consultas.hbs");
		  }
	  


}
