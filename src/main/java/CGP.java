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

/*	private Servicio buscarServicio(String nombreDelServicio) {
		Servicio elServicioBuscado = servicios.stream()
				.filter(unServicio -> unServicio.getNombre().equals(nombreDelServicio)).findFirst().get();
		return elServicioBuscado;

	}*/

	public boolean estaDisponible(LocalDateTime horario) {
		try {
			return servicios.stream().anyMatch(unServicio -> unServicio.estaDisponible(horario));

		} catch (NullPointerException exepcion) {
			// System.out.println("No existe el servicio " + busqueda);
			return false;
		}
	}
}
