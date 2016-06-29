import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import TypePois.Banco;
import TypePois.POI;

public class RepoPOIs {
	List<POI> puntosDeIntereses;
	static RepoPOIs instancia = new RepoPOIs();

	public static RepoPOIs getInstance() {
		if (instancia == null) {
			instancia = new RepoPOIs();
			instancia.inicializarPuntosDeIntereses();
		}
		return instancia;
	}

	public void inicializarPuntosDeIntereses() {
		puntosDeIntereses = new ArrayList<POI>();
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
