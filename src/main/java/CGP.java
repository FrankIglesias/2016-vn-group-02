import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class CGP extends POI {
	private int comuna;
	private List<Servicio> servicios = new ArrayList<Servicio>();

	public CGP(Direccion dir, String nombre) {
		super(dir, nombre);
	}

	public boolean estaDisponible(GregorianCalendar horario, Servicio servicio) {
		Servicio servicioBuscado = buscarServicio(servicio);
		return (servicioBuscado.estasDisponibleEn(horario));

	}

	public boolean estasCerca(Direccion unaDireccion) {
		return unaDireccion.getComuna() == (this.comuna);
	}

	private Servicio buscarServicio(Servicio servicio) {
		Servicio elServicioBuscado = servicios.stream().filter(unServicio -> equals(servicio)).findFirst().get();
		return elServicioBuscado;

	}

	public boolean estaDisponible(GregorianCalendar horario) {
		return servicios.stream().anyMatch(servicio -> servicio.estasDisponibleEn(horario));
	}
}
