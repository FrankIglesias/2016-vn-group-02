import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Horario {
	private Map <DayOfWeek, List<IntervaloHorario>> unHorario = new HashMap<DayOfWeek,List<IntervaloHorario>>();
	
	public Horario(){
	super();
	}
	public Horario(Map <DayOfWeek, List<IntervaloHorario>> horario) {
		super();
		unHorario = horario;
	}
	public void añadirIntervalo(DayOfWeek dia, List<IntervaloHorario> unIntervalo){
	unHorario.put(dia, unIntervalo);
	
	}
	
	public boolean incluyeHorario(LocalDateTime horario) {
		List<IntervaloHorario> intervaloHorario = unHorario.get(horario.getDayOfWeek());
		return intervaloHorario.stream().anyMatch(unHorario -> unHorario.incluyeHora(horario.toLocalTime()));
	}
}