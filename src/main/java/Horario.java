import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
<<<<<<< HEAD
=======
import java.util.Set;
>>>>>>> branch 'master' of https://github.com/dds-utn/2016-vn-group-02.git

public class Horario {
<<<<<<< HEAD
	private Map <DayOfWeek, List<IntervaloHorario>> unHorario = new HashMap<DayOfWeek,List<IntervaloHorario>>();
	
	public Horario(){
	super();
=======
	
	Map<DayOfWeek, List<IntervaloHorario>> agenda = new HashMap<>();
	
	public void setAgenda(DayOfWeek unDia, List<IntervaloHorario> unIntervalo)
	{
		agenda.put(unDia, unIntervalo);
>>>>>>> branch 'master' of https://github.com/dds-utn/2016-vn-group-02.git
	}
<<<<<<< HEAD
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
=======

	public boolean incluyeHorarioYDia(LocalDateTime DiaYHorario) {
		//Set<DayOfWeek> diasDeLaSemana = agenda.keySet();
		agenda.get(DiaYHorario.getDayOfWeek());
		if(diasDeLaSemana.stream().filter(unDia -> unDia = (horario.getDayOfWeek())));
		{
			retorno = intervaloHorario.stream().anyMatch
					(unHorario -> unHorario.incluyeHora(horario.toLocalTime()));
		}

		return retorno;
>>>>>>> branch 'master' of https://github.com/dds-utn/2016-vn-group-02.git
	}
}