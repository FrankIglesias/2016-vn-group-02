package Controllers;

import java.util.ArrayList;
import java.util.List;

import FiltrosDeBusqueda.Filtro;
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
	
	public List<Busqueda> listarBusquedas() {
		listaDeFiltros.stream().forEach(unFiltro -> listaDeBusquedas = unFiltro.aplicarFiltro(listaDeBusquedas));
		return listaDeBusquedas;
	}
	
	
}
