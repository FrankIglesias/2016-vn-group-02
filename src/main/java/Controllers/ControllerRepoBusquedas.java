package Controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import FiltrosDeBusqueda.DesdeHasta;
import FiltrosDeBusqueda.Filtro;
import FiltrosDeBusqueda.PorCantidadDePoiDevueltos;
import FiltrosDeBusqueda.PorTerminal;
import Repositorios.Busqueda;
import Repositorios.RepoDeBusquedas;
import Repositorios.RepoPOIs;
import TypePois.POI;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerRepoBusquedas implements WithGlobalEntityManager, TransactionalOps {

	private static ControllerRepoBusquedas instancia;
	List<Filtro> listaDeFiltros = new ArrayList<Filtro>();
	List<Busqueda> listaDeBusquedas = new ArrayList<Busqueda>();

	RepoDeBusquedas modelo = RepoDeBusquedas.getInstance();

	public static ControllerRepoBusquedas getInstance() {
		if (instancia == null) {
			instancia = new ControllerRepoBusquedas();
		}
		return instancia;
	}

	public Busqueda buscarUnaBusquedaPorId(String id) {
		return entityManager().find(Busqueda.class, Integer.parseInt(id));
	}

	public void addFiltro(Filtro unFiltro) {
		if (!listaDeFiltros.contains(unFiltro))
			listaDeFiltros.add(unFiltro);
	}

	public void removeFiltro(Filtro unFiltro) {
		listaDeFiltros.remove(unFiltro);
	}

	public List<Busqueda> listarBusquedas(String nombre, String desde, String hasta, String cantidad) {
		listaDeBusquedas = RepoDeBusquedas.getInstance().listarTodo();

		if (!nombre.isEmpty()) {
			listaDeFiltros.add(new PorTerminal(nombre));
		}
		if (!desde.isEmpty() && !hasta.isEmpty()) {
			listaDeFiltros.add(new DesdeHasta(LocalDateTime.parse(desde), LocalDateTime.parse(hasta)));
		}
		if (!cantidad.isEmpty()) {
			listaDeFiltros.add(new PorCantidadDePoiDevueltos(Integer.parseInt(cantidad)));
		}
		if (!listaDeFiltros.isEmpty())
			listaDeFiltros.stream().forEach(unFiltro -> listaDeBusquedas = unFiltro.aplicarFiltro(listaDeBusquedas));
		return listaDeBusquedas;
	}

	public ModelAndView mostrarConsultas(Request request, Response response) {
		System.out.println("Mostrar Consultas");
		return new ModelAndView(null, "admin_consultas.hbs");
	}

	public ModelAndView buscarBusquedas(Request request, Response response) {
		System.out.println("Buscando busquedas");
		HashMap<String, Object> viewModel = new HashMap<>();
		try {
			List<Busqueda> busquedas = ControllerRepoBusquedas.getInstance().listarBusquedas(
					request.queryParams("nombre"), request.queryParams("desde"), request.queryParams("hasta"),
					request.queryParams("cantidad"));
			viewModel.put("consultas", busquedas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(viewModel, "admin_consultas.hbs");
	}

	public ModelAndView verPoisConsultas(Request request, Response response) {
		System.out.println("Ver pois de una consulta");
		String idBusqueda = request.queryParams("id");
		HashMap<String, Object> viewModel = new HashMap<>();
		try {
			List<Integer> idsPOIs = ControllerRepoBusquedas.getInstance().buscarUnaBusquedaPorId(idBusqueda)
					.getPuntosBuscados();
			List<POI> poisDeLaBusqueda = new ArrayList<POI>();

			idsPOIs.forEach(
					unID -> poisDeLaBusqueda.add(RepoPOIs.getInstance().obtenerDeHibernateSegunId(unID.toString())));
			viewModel.put("listadoPOIs", poisDeLaBusqueda);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(viewModel, "ver_pois_consultas.hbs");
	}

}
