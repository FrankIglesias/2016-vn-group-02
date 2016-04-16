import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CGP extends POI {
	private int comuna;
	private List<Servicio> servicios = new ArrayList<Servicio>();

	public CGP(Direccion dir, String nombre, List<Servicio> listaDeServicios) {
		super(dir, nombre);
		servicios = listaDeServicios;
		servicios.stream().forEach(servicio -> addPalabraClave(servicio.getNombre()));
	}

	public boolean estasCerca(Direccion unaDireccion) {
		return unaDireccion.getComuna() == (comuna);
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
