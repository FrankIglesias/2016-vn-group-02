import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Banco extends POI {
	static ArrayList<Horario> horarioBancario = initializeHorarioBanco();

	public Banco(Geolocalizacion point, String nombre, ArrayList<String> palabrasClave/*, ArrayList<Horario> horario*/, List<Feriado> feriados) {
		super(point, nombre, palabrasClave, horarioBancario, feriados);
	}

	private static ArrayList<Horario> initializeHorarioBanco() {
		ArrayList<Horario> horario = new ArrayList<Horario>();
		List<IntervaloHorario> intervaloFijo = new ArrayList<IntervaloHorario>();
		IntervaloHorario intervalo = new IntervaloHorario(LocalTime.of(10, 00), LocalTime.of(15, 00));
		Horario lunes = new Horario(DayOfWeek.MONDAY, intervaloFijo);
		Horario martes = new Horario(DayOfWeek.TUESDAY, intervaloFijo);
		Horario miercoles = new Horario(DayOfWeek.WEDNESDAY, intervaloFijo);
		Horario jueves = new Horario(DayOfWeek.THURSDAY, intervaloFijo);
		Horario viernes = new Horario(DayOfWeek.FRIDAY, intervaloFijo);
		
		intervaloFijo.add(intervalo);
		horario.add(lunes);
		horario.add(martes);
		horario.add(miercoles);
		horario.add(jueves);
		horario.add(viernes);
		
		return horario;
	}

	public boolean estaDisponible(LocalDateTime horarioConsultado) {
		boolean retorno = false;
		
		if (estoyEnFeriado(horarioConsultado.toLocalDate())) {
			Feriado fecha = this.feriados.stream().filter
			(Feriado -> Feriado.getFecha() == horarioConsultado.toLocalDate()).findFirst().get();
			
			if(fecha.incluyeHorario(horarioConsultado)) {
				retorno = true;
			}
		}
		else if (horario.stream().anyMatch(unHorario -> unHorario.incluyeHorario(horarioConsultado)))
		{
			retorno = true;
		}
		
		return retorno;
	}
}
