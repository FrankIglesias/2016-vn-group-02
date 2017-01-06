package FiltrosDeBusqueda;

import java.util.List;
import java.util.stream.Collectors;

import Repositorios.Busqueda;

public class PorTerminal implements Filtro {

	private String nombreTerminal;

	public PorTerminal(String nombreTerminal) {
		this.nombreTerminal = nombreTerminal;
	}
	
	@Override
	public List<Busqueda> aplicarFiltro(List<Busqueda> listaDeBusquedas) {
		return listaDeBusquedas.stream().filter(unaBusqueda -> unaBusqueda.getTerminal().equals(nombreTerminal)).collect(Collectors.toList());
	}
	
	
}
