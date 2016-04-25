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
		
		horarioBancario.añadirIntervalo(DayOfWeek.MONDAY, intervaloBancario);
		horarioBancario.añadirIntervalo(DayOfWeek.TUESDAY, intervaloBancario);
		horarioBancario.añadirIntervalo(DayOfWeek.WEDNESDAY, intervaloBancario);
		horarioBancario.añadirIntervalo(DayOfWeek.THURSDAY, intervaloBancario);
		horarioBancario.añadirIntervalo(DayOfWeek.FRIDAY, intervaloBancario);
	}

}
