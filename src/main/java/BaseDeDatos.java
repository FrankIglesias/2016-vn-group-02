import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import TypePois.Banco;
import TypePois.POI;

public class BaseDeDatos {

	private static BaseDeDatos instancia = null;
	List<POI> puntosDeIntereses = new ArrayList<POI>();
	List<Busqueda> busquedas = new ArrayList<Busqueda>();
	Map<LocalDate, Integer> reportePorFecha = new HashMap<LocalDate, Integer>();
	Map<String, Integer> reporteBusquedasTotales = new HashMap<String, Integer>();
	List<Terminal> terminales = new ArrayList<Terminal>();

	public static BaseDeDatos getInstance() {
		if (instancia == null) {
			instancia = new BaseDeDatos();
		}
		return instancia;
	}

	public Busqueda addBusqueda(Terminal terminal, String frase, double tiempo, double tiempoMax) {
		Busqueda busqueda = new Busqueda(terminal, frase, tiempo, tiempoMax);
		busquedas.add(busqueda);
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

	public void agregarNuevosPoi(POI nuevoPOI) {
		puntosDeIntereses.add(nuevoPOI);
	}

	public void sacarPoi(POI POIaSacar) {
		puntosDeIntereses.remove(puntosDeIntereses.stream().filter(unPoi -> sonIguales(unPoi, POIaSacar))
				.collect(Collectors.toList()).get(1));

	}

	public int cantidadDePOI() {
		return puntosDeIntereses.size();
	}

	private boolean sonIguales(POI point1, POI point2) {
		return point1.getPoint().getLatitud() == point2.getPoint().getLatitud()
				&& point1.getPoint().getLongitud() == point2.getPoint().getLongitud();
		// dos point son iguales si estan exactamente en el mismo punto.
	}

	public void agregarVariosPoi(List<POI> listaDePoi) {
		listaDePoi.forEach(unPoi -> puntosDeIntereses.add(unPoi));

	}

	public void agregarVariosPoiDeListaDeBancos(List<Banco> listaDePoi) {
		listaDePoi.forEach(unPoi -> puntosDeIntereses.add(unPoi));

	}

}
