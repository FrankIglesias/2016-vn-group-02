package TypePois;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamTime.Feriado;
import DesignDreamTeamTime.HorarioYDia;
import DesignDreamTeamTime.IntervaloHorario;

public class Banco extends POI {

	private static HorarioYDia horarioBancario = new HorarioYDia();

	public Banco(Geolocalizacion point, String nombre, ArrayList<String> palabrasClave, List<Feriado> feriados) {
		super(point, nombre, palabrasClave, horarioBancario, feriados);
		IntervaloHorario intervaloUnico = new IntervaloHorario(LocalTime.of(10, 00), LocalTime.of(15, 00));
		List<IntervaloHorario> intervaloBancario = new ArrayList<IntervaloHorario>();
		intervaloBancario.add(intervaloUnico);

		horarioBancario.agregarIntervalo(DayOfWeek.MONDAY, intervaloBancario);
		horarioBancario.agregarIntervalo(DayOfWeek.TUESDAY, intervaloBancario);
		horarioBancario.agregarIntervalo(DayOfWeek.WEDNESDAY, intervaloBancario);
		horarioBancario.agregarIntervalo(DayOfWeek.THURSDAY, intervaloBancario);
		horarioBancario.agregarIntervalo(DayOfWeek.FRIDAY, intervaloBancario);
	}
}