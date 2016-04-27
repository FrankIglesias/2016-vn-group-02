import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;

public class CGP extends POI {
	private List<Servicio> servicios = new ArrayList<Servicio>();

	public CGP(Geolocalizacion point, String nombre, List<Servicio> servicios,
			List<Feriado> feriados) {
		super(point, nombre, new ArrayList<String>(), new HorarioYDia(),
				feriados);
		this.servicios = servicios;
		servicios.stream().forEach(
				servicio -> addPalabraClave(servicio.getNombre()));
	}

	public boolean estasCercaDeUnPunto(Geolocalizacion otroPoint) {
		return distanciaMenor(point.distanciaCon(otroPoint), 500);
	}

	public Servicio buscarServicio(String nombreDelServicio) {
		Servicio elServicioBuscado = servicios
				.stream()
				.filter(unServicio -> unServicio.getNombre().equals(
						nombreDelServicio)).findFirst().get();
		return elServicioBuscado;

	}

	public boolean estaDisponible(LocalDateTime horario) {
		return servicios.stream().anyMatch(
				unServicio -> estaDisponible(horario));
		/*
		 * boolean retorno = false;
		 * 
		 * Scanner keyboard = new Scanner(System.in);
		 * System.out.println("Buscar Servicio:"); String busqueda =
		 * keyboard.nextLine();
		 * 
		 * /*if (estoyEnFeriado(horario.toLocalDate())) { Feriado fecha =
		 * this.feriados.stream().filter (Feriado -> Feriado.getFecha() ==
		 * horario.toLocalDate()).findFirst().get();
		 * 
		 * if(fecha.incluyeHorario(horario)) { retorno = true; } } else if
		 * (busqueda.equals("")) {
		 */

		/*
		 * } else { try { return
		 * this.buscarServicio(busqueda).estaDisponible(horario);
		 * 
		 * } } catch (NullPointerException exepcion) { //
		 * System.out.println("No existe el servicio " + busqueda); return
		 * false; } } return retorno;
		 */
	}
}
