package FiltrosDeBusqueda;

import java.util.List;
import java.util.stream.Collectors;

import Repositorios.Busqueda;

public class PorCantidadDePoiDevueltos implements Filtro {

	private int cantidadParametro;
	
	public PorCantidadDePoiDevueltos(int cantidadParametro) {
		this.cantidadParametro = cantidadParametro;
	}
	
	@Override
	public List<Busqueda> aplicarFiltro(List<Busqueda> listaDeBusquedas) {
		return listaDeBusquedas.stream().filter(unaBusqueda -> unaBusqueda.cantidadDePoisObtenidos() >= cantidadParametro).collect(Collectors.toList());
	}

}
