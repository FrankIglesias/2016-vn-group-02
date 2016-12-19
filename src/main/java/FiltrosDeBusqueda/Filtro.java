package FiltrosDeBusqueda;

import java.util.List;
import Repositorios.Busqueda;

public interface Filtro {
	public List<Busqueda> aplicarFiltro(List<Busqueda> listaDeBusquedas);
}
