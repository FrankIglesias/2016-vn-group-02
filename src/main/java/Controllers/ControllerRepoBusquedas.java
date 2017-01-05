package Controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import FiltrosDeBusqueda.DesdeHasta;
import FiltrosDeBusqueda.Filtro;
import FiltrosDeBusqueda.PorCantidadDePoiDevueltos;
import FiltrosDeBusqueda.PorTerminal;
import Repositorios.Busqueda;
import Repositorios.RepoDeBusquedas;

public class ControllerRepoBusquedas implements WithGlobalEntityManager, TransactionalOps{
	
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
	
	public Busqueda buscarUnaBusquedaPorId(String id) 
	 {
	  return entityManager().find(Busqueda.class, Integer.parseInt(id));
	 }
	public void addFiltro(Filtro unFiltro) {
		if(!listaDeFiltros.contains(unFiltro))
		listaDeFiltros.add(unFiltro);
	}
	
	public void removeFiltro(Filtro unFiltro) {
		listaDeFiltros.remove(unFiltro);
	}
	
	public List<Busqueda> listarBusquedas(String nombre, String desde, String hasta, String cantidad) {
		listaDeBusquedas = RepoDeBusquedas.getInstance().listarTodo();
		
		if(!nombre.isEmpty()) { listaDeFiltros.add(new PorTerminal(nombre)); }
		if(!desde.isEmpty() && !hasta.isEmpty()) { listaDeFiltros.add(new DesdeHasta(LocalDateTime.parse(desde), LocalDateTime.parse(hasta))); }
		if(!cantidad.isEmpty()) { listaDeFiltros.add(new PorCantidadDePoiDevueltos(Integer.parseInt(cantidad))); }
		if(!listaDeFiltros.isEmpty())
		listaDeFiltros.stream().forEach(unFiltro -> listaDeBusquedas = unFiltro.aplicarFiltro(listaDeBusquedas));
		return listaDeBusquedas;
	}
	
	
}
