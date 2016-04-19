import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CGP extends POI {
	private List<Servicio> servicios = new ArrayList<Servicio>();
	
	public CGP(Geolocalizacion point, String nombre, List<Servicio> servicios) {
		super(point, nombre, new ArrayList<String>(), new ArrayList<Horario>());
		this.servicios = servicios;
		servicios.stream().forEach(servicio -> addPalabraClave(servicio.getNombre()));
	}

	public boolean estasCerca(Geolocalizacion otroPoint) {
	return point.distanciaCon(otroPoint) < 500;
	 }

	private Servicio buscarServicio(String nombreDelServicio) {
		Servicio elServicioBuscado = servicios.stream()
				.filter(unServicio -> unServicio.getNombre().equals(nombreDelServicio)).findFirst().get();
		return elServicioBuscado;

	}

	@Override
	public boolean estaDisponible(LocalDateTime horario) {
		boolean retorno = false;

		Scanner keyboard = new Scanner(System.in);
		System.out.println("Buscar Servicio:");
		String busqueda = keyboard.nextLine();

		if (busqueda.equals("")) {
			retorno = servicios.stream().anyMatch(unServicio -> estaDisponible(horario));

		} else {
			try {
				if (this.buscarServicio(busqueda).estaDisponible(horario)) {
					retorno = true;
				}
			} catch (NullPointerException exepcion) {
				// System.out.println("No existe el servicio " + busqueda);
				return false;
			}
		}
		return retorno;
	}
}
