import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BaseDeDatos {
	static List<Busqueda> busquedas = new ArrayList<Busqueda>();
	Map<LocalDate, Integer> reportePorFecha = new HashMap<LocalDate, Integer>();
	Administrador admin;
	
	BaseDeDatos(){
		super();
		admin = new Administrador();
	}

	public void addBusqueda(Terminal terminal, String frase, double tiempo) {
		Busqueda busqueda = new Busqueda(terminal, frase, tiempo);
		busquedas.add(busqueda);
		admin.notificar(busqueda);
	}

	public int cantidadDeBusquedasPorFecha(LocalDate fecha) {
		return busquedas.stream().filter(unaBusqueda -> unaBusqueda.conFechaDe(fecha)).collect(Collectors.toList())
				.size();
	}
	
	public void addBusquedasPorFechaAlReporte(LocalDate fecha){
		reportePorFecha.put(fecha, this.cantidadDeBusquedasPorFecha(fecha));
	}
	
	public Map<LocalDate, Integer> getReportePorFecha(){
		return reportePorFecha;
	}
	
	
}
