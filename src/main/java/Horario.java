
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Horario {

	private DayOfWeek day;
	private LocalTime horaInicial;
	private LocalTime horaFinal;

	public Horario(DayOfWeek day, LocalTime horaInicial, LocalTime horaFinal) {
		super();
		this.day = day;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
	}

	public DayOfWeek getDay() {
		return day;
	}

	public void setDay(DayOfWeek day) {
		this.day = day;
	}

	public LocalTime getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(LocalTime horaInicial) {
		this.horaInicial = horaInicial;
	}

	public LocalTime getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(LocalTime horaFinal) {
		this.horaFinal = horaFinal;
	}

	public boolean estaDisponibleEnHorario(LocalDateTime horario) {
		boolean retorno = false;

		if (horario.getDayOfWeek() == this.day && horario.toLocalTime().isAfter(horaInicial)
				&& horario.toLocalTime().isBefore(horaFinal)) {
			retorno = true;
		}

		return retorno;
	}
}