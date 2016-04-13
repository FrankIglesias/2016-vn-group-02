import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class CGP extends POI {
	private int comuna;
	private List<Servicio> servicios = new ArrayList<Servicio>();

	public CGP(Direccion dir, String nombre) {
		super(dir, nombre);
	}

	public boolean estaDisponible(GregorianCalendar horario, Servicio servicio) {
		Servicio servicioBuscado = this.buscarServicio(servicio);
		return (servicioBuscado.estasDisponibleEn(horario));

	}

	public boolean estasCerca(Direccion unaDireccion) {
		return unaDireccion.getComuna() == (this.comuna);
	}

	public boolean estaDisponible(Date horario) {
		return servicios.stream().anyMatch(servicio -> servicio.estasDisponibleEn(horario));
	}

	private Servicio buscarServicio(Servicio servicio) {
		Servicio elServicioBuscado = servicios.stream().filter(unServicio -> equals(servicio)).findFirst().get();
		return elServicioBuscado;

	}
}
