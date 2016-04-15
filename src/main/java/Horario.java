
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Horario {

	private DayOfWeek day;
	private ArrayList<IntervaloHorario> intervaloHorario;

	public DayOfWeek getDay() {
		return day;
	}
	public void setDay(DayOfWeek day) {
		this.day = day;
	}
	public ArrayList<IntervaloHorario> getIntervaloHorario() {
		return intervaloHorario;
	}
	public void setIntervaloHorario(ArrayList<IntervaloHorario> intervaloHorario) {
		this.intervaloHorario = intervaloHorario;
	}
	public Horario(DayOfWeek day, ArrayList<IntervaloHorario> intervaloHorario) {
		super();
		this.day = day;
		this.intervaloHorario = intervaloHorario;
	}
	
	public boolean incluyeHorario(LocalDateTime horario) {
		boolean retorno = false;

		if (horario.getDayOfWeek() == this.day) {
			for(int i = 0; i < intervaloHorario.size(); i++){
				if(intervaloHorario.get(i).incluyeHora(horario.toLocalTime()))
					retorno = true;
			}
		}

		return retorno;
	}
}