import java.time.LocalDateTime;
import java.util.ArrayList;

public class Banco extends POI {
	private ArrayList<Horario> horario;

	public Banco(Direccion dir, String nombre, ArrayList<Horario> horarioBancario) {
		super(dir, nombre);
		horario = horarioBancario;
	}

	public boolean estaDisponible(LocalDateTime horarioConsultado) {
		return horario.stream().anyMatch(unHorario -> unHorario.incluyeHorario(horarioConsultado));
	}
	public boolean estaDisponible(LocalDateTime horario, Servicio servicio) {
		return estaDisponible(horario);
	}
}
