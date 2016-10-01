package TypePois;

import java.time.DayOfWeek;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamTime.Feriado;
import DesignDreamTeamTime.GestorIntervalos;
import DesignDreamTeamTime.HorarioYDia;
import DesignDreamTeamTime.IntervaloHorario;
@Entity
public class Banco extends POI {
		
	//@OneToOne(cascade = CascadeType.PERSIST)
	private static HorarioYDia horarioBancario = new HorarioYDia();

	public Banco()
	{
		
	}
	public Banco(Geolocalizacion point, String nombre, ArrayList<String> palabrasClave, List<Feriado> feriados) {
		super(point, nombre, palabrasClave, horarioBancario, feriados);
		IntervaloHorario intervaloUnico = new IntervaloHorario(LocalTime.of(10, 00), LocalTime.of(15, 00));
		ArrayList<IntervaloHorario> intervaloBancario = new ArrayList<IntervaloHorario>();
		intervaloBancario.add(intervaloUnico);
		GestorIntervalos gestor = new GestorIntervalos(intervaloBancario);

		horarioBancario.agregarIntervalo(DayOfWeek.MONDAY, gestor);
		horarioBancario.agregarIntervalo(DayOfWeek.TUESDAY, gestor);
		horarioBancario.agregarIntervalo(DayOfWeek.WEDNESDAY, gestor);
		horarioBancario.agregarIntervalo(DayOfWeek.THURSDAY, gestor);
		horarioBancario.agregarIntervalo(DayOfWeek.FRIDAY, gestor);
	}
}