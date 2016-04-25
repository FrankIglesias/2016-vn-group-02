
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Horario {
	
	Map<DayOfWeek, List<IntervaloHorario>> agenda = new HashMap<>();
	
	public void setAgenda(DayOfWeek unDia, List<IntervaloHorario> unIntervalo)
	{
		agenda.put(unDia, unIntervalo);
	}

	public boolean incluyeHorarioYDia(LocalDateTime DiaYHorario) {
		//Set<DayOfWeek> diasDeLaSemana = agenda.keySet();
		agenda.get(DiaYHorario.getDayOfWeek());
		if(diasDeLaSemana.stream().filter(unDia -> unDia = (horario.getDayOfWeek())));
		{
			retorno = intervaloHorario.stream().anyMatch
					(unHorario -> unHorario.incluyeHora(horario.toLocalTime()));
		}

		return retorno;
	}
}