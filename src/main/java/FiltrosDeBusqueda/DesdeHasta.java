package FiltrosDeBusqueda;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import Repositorios.Busqueda;

public class DesdeHasta implements Filtro {

	private LocalDateTime desde;
	private LocalDateTime hasta;

	public DesdeHasta(LocalDateTime desde, LocalDateTime hasta) {
		this.desde = desde;
		this.hasta = hasta;
	}
	
	@Override
	public List<Busqueda> aplicarFiltro(List<Busqueda> listaDeBusquedas) {
		return listaDeBusquedas.stream().filter(unaBusqueda -> unaBusqueda.estaEntreDosFechas(desde,hasta)).collect(Collectors.toList());
	}

}
