package MainPackage;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MainController {
	  public ModelAndView mostrar(Request request, Response response) {
		    return new ModelAndView(null, "home.hbs");
		  }

}
