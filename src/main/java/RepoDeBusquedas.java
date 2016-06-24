import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import TypePois.Banco;
import TypePois.POI;

public class RepoDeBusquedas {

	private static RepoDeBusquedas instancia = null;
	List<Busqueda> busquedas;
	Map<LocalDate, Integer> reportePorFecha;
	Map<String, Integer> reporteBusquedasTotales;
	List<Terminal> terminales;

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
		reporteBusquedasTotales = new HashMap<String, Integer>();
		terminales = new ArrayList<Terminal>();
	}

	public Busqueda addBusqueda(Terminal terminal, String frase, double tiempo, double tiempoMax) {
		Busqueda busqueda = new Busqueda(terminal, frase, tiempo, tiempoMax);
		busquedas.add(busqueda);
		addBusquedasPorFechaAlReporte(busqueda.getFecha());
		if (!terminales.contains(terminal)) {
			terminales.add(terminal);
		}
		return busqueda;
	}

	public int cantidadDeBusquedasPorFecha(LocalDate fecha) {
		return busquedas.stream().filter(unaBusqueda -> unaBusqueda.conFechaDe(fecha)).collect(Collectors.toList())
				.size();
	}

	public void addBusquedasPorFechaAlReporte(LocalDate fecha) {
		reportePorFecha.put(fecha, this.cantidadDeBusquedasPorFecha(fecha));
	}

	public Map<LocalDate, Integer> getReportePorFecha() {
		return reportePorFecha;
	}

	public void addReportesPorTerminal() {

		for (Terminal unaTerminal : terminales) {
			reporteBusquedasTotales.put(unaTerminal.getNombre(), unaTerminal.resultadosTotales());
		}

	}

	public Map<String, Integer> getReporteBusquedasTotales() {
		addReportesPorTerminal();
		return reporteBusquedasTotales;
	}

}
