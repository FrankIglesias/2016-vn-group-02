package DesignDreamTeamTime;
import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//@Entity
public class HorarioYDia {
	
	@ElementCollection
	@JoinTable(name="Agenda", joinColumns=@JoinColumn(name="id_agenda"))
	@MapKeyColumn (name="dia")
	@Column(name="VALUE")
	private Map<DayOfWeek, GestorIntervalos> agenda = new HashMap<DayOfWeek, GestorIntervalos>();

	public HorarioYDia() {
		super();
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
		ArrayList<IntervaloHorario> intervaloHorario = agenda.get(horario.getDayOfWeek()).getIntervalosHorarios();

		if (diasDeLaAgenda.stream().filter(unDia -> unDia == (horario.getDayOfWeek())) != null) {
			return intervaloHorario.stream().anyMatch(unIntervalo -> unIntervalo.incluyeHora(horario.toLocalTime()));
		} else {
			return false;
		}
	}

}