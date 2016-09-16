package Repositorios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import TypePois.POI;

public class RepoDeBusquedas {

	private static RepoDeBusquedas instancia = null;
	List<Busqueda> busquedas;
	Map<LocalDate, Integer> reportePorFecha;
	RepoTerminales repoTerminales;

	public static RepoDeBusquedas getInstance() {
		if (instancia == null) {
			instancia = new RepoDeBusquedas();
			instancia.inicializarBaseDeDatos();
		}
		return instancia;
	}

	public void inicializarBaseDeDatos() {
		busquedas = new ArrayList<Busqueda>();
		reportePorFecha = new HashMap<LocalDate, Integer>();
		repoTerminales = RepoTerminales.getInstance();
	}

	public Busqueda addBusqueda(Terminal terminal, String frase, double tiempo, double tiempoMax,
			List<POI> puntosObtenidos) {
		Busqueda busqueda = new Busqueda(terminal, frase, tiempo, tiempoMax, puntosObtenidos);
		busquedas.add(busqueda);
		addBusquedasPorFechaAlReporte(busqueda.getFecha());
		repoTerminales.add(terminal);
		return busqueda;
	}

	public int cantidadDeBusquedasPorFecha(LocalDate fecha) {
		return busquedas.stream().filter(unaBusqueda -> unaBusqueda.esDeLaFecha(fecha)).collect(Collectors.toList())
				.size();
	}

	public void addBusquedasPorFechaAlReporte(LocalDate fecha) {
		reportePorFecha.put(fecha, this.cantidadDeBusquedasPorFecha(fecha));
	}

	public Map<LocalDate, Integer> getReportePorFecha() {
		return reportePorFecha;
	}

}
