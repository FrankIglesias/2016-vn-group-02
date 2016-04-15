import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Banco extends POI {
	private ArrayList<Horario> horario;

	public Banco(Direccion dir, String nombre, ArrayList<Horario> horario) {
		super(dir, nombre);
		horario = horario;
	}

//	public boolean estaDisponible(LocalDateTime horarioConsultado) {
//		return horario.estaDisponibleEnHorario(horarioConsultado);
//	}
	public boolean estaDisponible(LocalDateTime horario, Servicio servicio) {
		return estaDisponible(horario);
	}
}
