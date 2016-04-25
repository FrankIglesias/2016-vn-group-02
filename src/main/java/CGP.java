import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CGP extends POI {
	private List<Servicio> servicios = new ArrayList<Servicio>();

	public CGP(Geolocalizacion point, String nombre, List<Servicio> servicios, List<Feriado> feriados) {
		super(point, nombre, new ArrayList<String>(), new ArrayList<Horario>(), feriados);
		this.servicios = servicios;
		
		servicios.stream().forEach(servicio -> addPalabraClave(servicio.getNombre()));
	}

	public boolean estasCerca(Geolocalizacion otroPoint) {
		return point.distanciaCon(otroPoint) < 500;
	}

	public boolean estaDisponible(LocalDateTime horario) {
		if (!this.servicios.isEmpty()) 
			return servicios.stream().anyMatch(unServicio -> unServicio.estaDisponible(horario));
			return false;
		}
	}

