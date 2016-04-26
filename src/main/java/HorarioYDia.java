import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HorarioYDia {
	private Map <DayOfWeek, List<IntervaloHorario>> agenda = new HashMap<DayOfWeek,List<IntervaloHorario>>();
	
	public HorarioYDia(){
	super();
	}
	
	public HorarioYDia(Map <DayOfWeek, List<IntervaloHorario>> horario) {
		super();
		agenda = horario;
	}
	public void añadirIntervalo(DayOfWeek dia, List<IntervaloHorario> unIntervalo){
	agenda.put(dia, unIntervalo);
	
	}
	
	public boolean incluyeHorario(LocalDateTime horario) {
		List<IntervaloHorario> intervaloHorario = agenda.get(horario.getDayOfWeek());
		return intervaloHorario.stream().anyMatch(unIntervalo -> unIntervalo.incluyeHora(horario.toLocalTime()));
	}
}