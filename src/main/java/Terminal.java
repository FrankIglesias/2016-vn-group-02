import java.util.ArrayList;
import java.util.List;

public class Terminal {
	
	String nombre;
	static List<Integer> reporteParcialPorTerminal = new ArrayList<Integer>();
	
	public Terminal(String nombre){
		this.nombre = nombre;
	}
	
	public int cantidadDeResultadosPorBusqueda(List<POI> puntosSegunPalabra){
		return puntosSegunPalabra.size();	
	}
	
	public void addResultadosParcialesAlReporte(List<POI> puntosSegunPalabra){
		reporteParcialPorTerminal.add(cantidadDeResultadosPorBusqueda(puntosSegunPalabra));
	}
	
	public List<Integer> getReporteParcialPorTerminal(){
		return reporteParcialPorTerminal;
	}
	
	public int sumarResultados(List<Integer> enteros){
		int resultado = 0;
		for(Integer entero : enteros){
			resultado += entero;
		}
		return resultado;
	}
	
	public int resultadosTotales(){
		return this.sumarResultados(reporteParcialPorTerminal);
	}
	
	public String getNombre(){
		return nombre;
	}
}
