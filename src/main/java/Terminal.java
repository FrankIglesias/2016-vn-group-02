import java.util.ArrayList;
import java.util.List;

public class Terminal {
	
	static List<Integer> reporteParcialPorTerminal = new ArrayList<Integer>();
	
	public int cantidadDeResultadosPorBusqueda(List<POI> puntosSegunPalabra){
		return puntosSegunPalabra.size();	
	}
	
	public void addResultadosParcialesAlReporte(List<POI> puntosSegunPalabra){
		reporteParcialPorTerminal.add(cantidadDeResultadosPorBusqueda(puntosSegunPalabra));
	}
	
	public List<Integer> getReporteParcialPorTerminal(){
		return reporteParcialPorTerminal;
	}
}
