package MainPackage;

import java.util.HashMap;
import java.util.List;

import Repositorios.Buscador;
import Repositorios.Terminal;
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
		    return new ModelAndView(null, "admin_pois.hbs");
		  }
	  
	  public ModelAndView mostrarConsultas(Request request, Response response) {
		    return new ModelAndView(null, "admin_consultas.hbs");
		  }
	  
	  public ModelAndView filtrarNombreTipoPois(Request request, Response response) {
		  System.out.print(request.queryParams("nombre"));
		  System.out.print(response.toString());
		  if(request.queryParams("nombre") != null){
			  System.out.print("Si estoy aca ahora esta mal");
			  HashMap<String, Object> viewModel = new HashMap<>();
			  List<POI> resultado= new Buscador().buscarPoisHibernate(request.queryParams("nombre"), new Terminal("Admin"));
			  

				viewModel.put("filtroTerminal", request.queryParams("filtroTerminal"));

			}
		    return new ModelAndView(null, "admin_pois.hbs");
	  }
	  


}
