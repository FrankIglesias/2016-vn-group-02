import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Banco extends POI {
	
	private static Horario horarioBancario = new Horario();

	public Banco(Geolocalizacion point, String nombre, ArrayList<String> palabrasClave, List<Feriado> feriados) {
		super(point, nombre, palabrasClave, null, feriados);
		initializeHorarioBanco();
	}

	private static void initializeHorarioBanco() {
		 //
		
		IntervaloHorario intervaloUnico = new IntervaloHorario(LocalTime.of(10, 00), LocalTime.of(15, 00));
		List<IntervaloHorario> intervaloBancario = new ArrayList<IntervaloHorario>();
		intervaloBancario.add(intervaloUnico);
		
		horarioBancario.aņadirIntervalo(DayOfWeek.MONDAY, intervaloBancario);
		horarioBancario.aņadirIntervalo(DayOfWeek.TUESDAY, intervaloBancario);
		horarioBancario.aņadirIntervalo(DayOfWeek.WEDNESDAY, intervaloBancario);
		horarioBancario.aņadirIntervalo(DayOfWeek.THURSDAY, intervaloBancario);
		horarioBancario.aņadirIntervalo(DayOfWeek.FRIDAY, intervaloBancario);
	}

}
