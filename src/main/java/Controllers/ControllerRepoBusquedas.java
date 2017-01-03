package Controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import FiltrosDeBusqueda.DesdeHasta;
import FiltrosDeBusqueda.Filtro;
import FiltrosDeBusqueda.PorCantidadDePoiDevueltos;
import FiltrosDeBusqueda.PorTerminal;
import Repositorios.Busqueda;
import Repositorios.RepoDeBusquedas;

public class ControllerRepoBusquedas {
	
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
	
	public void addFiltro(Filtro unFiltro) {
		if(!listaDeFiltros.contains(unFiltro))
		listaDeFiltros.add(unFiltro);
	}
	
	public void removeFiltro(Filtro unFiltro) {
		listaDeFiltros.remove(unFiltro);
	}
	
	public List<Busqueda> listarBusquedas(String nombre, String desde, String hasta, String cantidad) {
		listaDeBusquedas = RepoDeBusquedas.getInstance().listarTodo();
		
		if(nombre != "") { listaDeFiltros.add(new PorTerminal(nombre)); }
		if(desde != "" && hasta != "") { listaDeFiltros.add(new DesdeHasta(LocalDateTime.parse(desde), LocalDateTime.parse(hasta))); }
		if(nombre != "") { listaDeFiltros.add(new PorCantidadDePoiDevueltos(Integer.parseInt(cantidad))); }
		
		listaDeFiltros.stream().forEach(unFiltro -> listaDeBusquedas = unFiltro.aplicarFiltro(listaDeBusquedas));
		return listaDeBusquedas;
	}
	
	
}
