package DesignDreamTeamTime;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@javax.persistence.Entity
public class HorarioYDia {
	
	@Id
	@GeneratedValue
	private int id;
	private Map<DayOfWeek, GestorIntervalos> agenda = new HashMap<DayOfWeek, GestorIntervalos>();

	public HorarioYDia() {
		super();
	}
	
	

	public int getId()
	{
		return this.id;
	}
	
	public void setId(int unID)
	{
		this.id = unID;
	}
	
	public HorarioYDia(Map<DayOfWeek, GestorIntervalos> horario) {
		super();
		agenda = horario;
	}

	public void agregarIntervalo(DayOfWeek dia, GestorIntervalos unGestor) {
		
		agenda.put(dia, unGestor);

	}

	public void setAgenda(Map<DayOfWeek, GestorIntervalos> unaAgenda) {
		this.agenda = unaAgenda;
	}

	public boolean incluyeHorario(LocalDateTime horario) {

		Set<DayOfWeek> diasDeLaAgenda = agenda.keySet();
		List<IntervaloHorario> intervaloHorario = agenda.get(horario.getDayOfWeek()).getIntervalosHorarios();

		if (diasDeLaAgenda.stream().filter(unDia -> unDia == (horario.getDayOfWeek())) != null) {
			return intervaloHorario.stream().anyMatch(unIntervalo -> unIntervalo.incluyeHora(horario));
		} else {
			return false;
		}
	}

}