package Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import AsignarAccionesUsuario.Accion;
import AsignarAccionesUsuario.AccionDesactivar;
import AsignarAccionesUsuario.AccionNotificarAdmin;
import DesignDreamTeamLocation.Domicilio;
import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamLocation.Localidad;
import Repositorios.RepoTerminales;
import Repositorios.Terminal;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerRepoTerminales {

	private static ControllerRepoTerminales instancia = null;
	public RepoTerminales modeloTerminales = RepoTerminales.getInstance();

	public static ControllerRepoTerminales getInstance() {
		if (instancia == null) {
			instancia = new ControllerRepoTerminales();
		}
		return instancia;
	}

	public List<Terminal> listarTerminales(String nombre, int comuna) {
		return modeloTerminales.obtenerTerminales(nombre, comuna);
	}

	public void eliminarUnaTerminal(Terminal unaTerminal) {
		modeloTerminales.eliminarUnaTerminal(unaTerminal);
	}

	public Terminal agregarUnaTerminal(String nombre, int comuna, String latitud, String longitud) {
		Terminal terminal = new Terminal(nombre, comuna);
		terminal.getPoint().setLatitud(Double.parseDouble(latitud));
		terminal.getPoint().setLongitud(Double.parseDouble(longitud));
		modeloTerminales.persistirTerminal(terminal);
		return terminal;
	}

	public void editarUnaTerminal(String nombre, int comuna, String callePrincipal, String entreCalles, String altura,
			String piso, String unidad, String codigoPostal, String unaCiudad, String unaProvincia, String unPais,
			double latitud, double longitud) {

		Terminal unaTerminal = modeloTerminales.buscameUnaTerminal(nombre);
		eliminarUnaTerminal(unaTerminal);

		Domicilio unDomi = new Domicilio(callePrincipal, entreCalles, altura, piso, unidad, codigoPostal, comuna);
		Localidad unaLoca = new Localidad(unaCiudad, unaProvincia, unPais);
		Geolocalizacion unaGeo = new Geolocalizacion(latitud, longitud, unDomi, unaLoca);

		Terminal laNuevaTerminal = new Terminal(nombre, comuna);
		laNuevaTerminal.setPoint(unaGeo);

		modeloTerminales.persistirTerminal(laNuevaTerminal);
	}

	public List<String> listarAccionesTerminal(Terminal unaTerminal) {
		return unaTerminal.getNombreDeAcciones();
	}

	public void setearAccionParaUnaTerminal(String nombreTerminal, Accion unaAccion) {
		Terminal unaTerminal = modeloTerminales.buscameUnaTerminal(nombreTerminal);
		eliminarUnaTerminal(unaTerminal);
		unaTerminal.addAccion(unaAccion);
		modeloTerminales.persistirTerminal(unaTerminal);

	}

	public void removerAccionParaUnaTerminal(String nombreTerminal, Accion unaAccion) {
		Terminal unaTerminal = modeloTerminales.buscameUnaTerminal(nombreTerminal);
		eliminarUnaTerminal(unaTerminal);
		unaTerminal.quitar(unaAccion);
		modeloTerminales.persistirTerminal(unaTerminal);
	}
	public ModelAndView mostrarTerminales(Request request, Response response) {
		System.out.println("Mostrar Admin Terminales");
		return new ModelAndView(null, "admin_terminales.hbs");
	}
	public ModelAndView buscarTerminal(Request request, Response response) {
		System.out.println("Buscar Terminal");
		HashMap<String, Object> viewModel = new HashMap<>();
		String nombre;
		try {
			if (request.queryParams("nombre").equals("")) {
				nombre = "";
			} else {
				nombre = request.queryParams("nombre");
			}
			int comuna;
			if (request.queryParams("comuna").equals("")) {
				comuna = -1;
			} else {
				comuna = Integer.parseInt(request.queryParams("comuna"));
			}
			List<Terminal> terminales = ControllerRepoTerminales.getInstance().listarTerminales(nombre, comuna);
			viewModel.put("terminales", terminales);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return new ModelAndView(viewModel, "admin_terminales.hbs");
	}
	public ModelAndView mostrarEditarTerminal(Request request, Response response) {
		System.out.println("Editar Terminal");
		String nombreFiltro = request.queryParams("nombre");
		HashMap<String, Object> viewModel = new HashMap<>();
		try {
			List<Terminal> terminales = ControllerRepoTerminales.getInstance().listarTerminales(nombreFiltro, -1);
			viewModel.put("terminales", terminales.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(viewModel, "editar_terminal.hbs");
	}

	public ModelAndView modificarTerminal(Request request, Response response) {
		System.out.println("Guardar una terminal");
		String nombre = request.queryParams("nombre");
		String nombreViejo = request.queryParams("nombreViejo");
		String comuna = request.queryParams("comuna");
		try {
			Terminal nuevaTerminal;
			Terminal terminalAModificar = RepoTerminales.getInstance().buscameUnaTerminal(nombreViejo);
			RepoTerminales.getInstance().eliminarUnaTerminal(terminalAModificar);
			nuevaTerminal = new Terminal(nombre, Integer.parseInt(comuna));
			terminalAModificar.getListaDeAcciones().forEach(a -> nuevaTerminal.addAccion(a));
			RepoTerminales.getInstance().add(nuevaTerminal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(null, "admin_terminales.hbs");
	}
	public ModelAndView mostrarAdminAcciones(Request request, Response response) {
		System.out.println("Administrar acciones por terminal");
		String nombre = request.queryParams("nombre");
		HashMap<String, Object> viewModel = new HashMap<>();
		try {
			Terminal terminal = RepoTerminales.getInstance().buscameUnaTerminal(nombre);
			viewModel.put("acciones", terminal.getListaDeAcciones());
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<String> accionesDisponnibles = new ArrayList<String>();
		accionesDisponnibles.add(new AccionNotificarAdmin().getNombre());
		accionesDisponnibles.add(new AccionDesactivar(null).getNombre());
		viewModel.put("acciones", accionesDisponnibles);
		viewModel.put("nombre", request.queryParams("nombre"));
		return new ModelAndView(viewModel, "admin_acciones.hbs");
	}
	public ModelAndView nuevaTerminal(Request request, Response response) {
		System.out.println("Nueva terminal");
		return new ModelAndView(null, "nueva_terminal.hbs");
	}
	public ModelAndView agregarTerminal(Request request, Response response) {
		System.out.println("Se agrego terminal");
		try {
			ControllerRepoTerminales.getInstance().agregarUnaTerminal(request.queryParams("nombre"),
					Integer.parseInt(request.queryParams("comuna").trim()), request.queryParams("latitud").trim(),
					request.queryParams("longitud").trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(null, "admin_terminales.hbs");

	}
	public Void borrarTerminal(Request request, Response response) {
		System.out.println("Se quiso borrar terminal nombre: " + request.queryParams("nombre"));
		try {

			Terminal terminalABorrar = RepoTerminales.getInstance().buscameUnaTerminal(request.queryParams("nombre"));
			ControllerRepoTerminales.getInstance().eliminarUnaTerminal(terminalABorrar);

		} catch (Exception e) {
			e.printStackTrace();
		}
		response.redirect("admin_terminales");
		return null;
	}
	public ModelAndView nuevaAccion(Request request, Response response) {
		System.out.println("Nueva Accion");
		String nombre = request.queryParams("nombre");
		String accion = request.queryParams("accion");

		Terminal terminalVieja = RepoTerminales.getInstance().buscameUnaTerminal(nombre);
		Terminal terminalNueva = new Terminal(terminalVieja.getNombre(), terminalVieja.getComuna());
		Domicilio unaDomi = new Domicilio(terminalVieja.getPoint().getDomicilio().getCallePrincipal(), terminalVieja.getPoint().getDomicilio().getEntreCalles(), terminalVieja.getPoint().getDomicilio().getAltura(), "0", "0", terminalVieja.getPoint().getDomicilio().getCodigoPostal(), terminalVieja.getPoint().getDomicilio().getComuna());
		Localidad unaLoca = new Localidad(terminalVieja.getPoint().getLocalidad().getCiudad(), terminalVieja.getPoint().getLocalidad().getProvincia(), terminalVieja.getPoint().getLocalidad().getPais());
		Geolocalizacion unaGeo = new Geolocalizacion(terminalVieja.getPoint().getLatitud(), terminalVieja.getPoint().getLongitud(), unaDomi, unaLoca);
		terminalNueva.setPoint(unaGeo);

		switch(accion.trim()) {
			case "mail_admin": terminalNueva.addAccion(new AccionNotificarAdmin());
		}
		
		try {
			ControllerRepoTerminales.getInstance().eliminarUnaTerminal(terminalVieja);
			RepoTerminales.getInstance().persistirTerminal(terminalNueva);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(null, "admin_acciones.hbs");
	}
}