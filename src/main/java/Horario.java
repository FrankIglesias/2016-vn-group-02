
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Horario {

	private DayOfWeek day;
	private List<IntervaloHorario> intervaloHorario;

	public DayOfWeek getDay() {
		return day;
	}

	public void setDay(DayOfWeek unDia) {
		day = unDia;
	}

	public List<IntervaloHorario> getIntervaloHorario() {
		return intervaloHorario;
	}

	public void setIntervaloHorario(List<IntervaloHorario> intervaloHorario) {
		this.intervaloHorario = intervaloHorario;
	}

	public Horario(DayOfWeek day, List<IntervaloHorario> intervaloHorario) {
		super();
		this.day = day;
		this.intervaloHorario = intervaloHorario;
	}

	public boolean incluyeHorario(LocalDateTime horario) {
		boolean retorno = false;

		if (horario.getDayOfWeek() == this.day) {
			retorno = intervaloHorario.stream().anyMatch
					(unHorario -> unHorario.incluyeHora(horario.toLocalTime()));
		}

		return retorno;
	}
}