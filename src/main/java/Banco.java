import java.util.GregorianCalendar;

public class Banco extends POI {
	private Horario horarioBancario;

	public Banco(Direccion dir, String nombre) {
		super(dir, nombre);
		horarioBancario = new Horario(1, 5, 10, 15);
	}

	public boolean estaDisponible(GregorianCalendar horario) {
		return horarioBancario.estaEntreLosHorarios(horario);
	}
	public boolean estaDisponible(GregorianCalendar horario, Servicio servicio) {
		return estaDisponible(horario);
	}
}
