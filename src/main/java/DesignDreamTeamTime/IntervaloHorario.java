package DesignDreamTeamTime;
import javax.persistence.*;

import org.uqbarproject.jpa.java8.extras.convert.LocalDateTimeConverter;

import java.awt.List;
import java.time.LocalTime;
import java.util.ArrayList;

@Entity
public class IntervaloHorario {

	@Id
	@GeneratedValue
	private Long id;
	
	@Convert(converter=LocalDateTimeConverter.class)
	LocalTime horaInicio;
	@Convert(converter=LocalDateTimeConverter.class)
	LocalTime horaFin;
	@Convert(converter=LocalDateTimeConverter.class)
	LocalTime mediaNoche = LocalTime.MIDNIGHT;
	
	
	public IntervaloHorario(LocalTime horaInicial, LocalTime horaFinal) {
		super();
		horaInicio = horaInicial;
		horaFin = horaFinal;
	
	}
	
	public boolean incluyeHora(LocalTime hora) {
		if (horaInicio == hora){
			return true;
		} else if(horaFin == hora){
			return false;
		}
		if (horaFin.isBefore(horaInicio))
			return (between(hora, horaInicio, mediaNoche) || between(hora, mediaNoche, horaFin));

		return (between(hora, horaInicio, horaFin));

	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	private boolean between(LocalTime horaACheckear, LocalTime hora1, LocalTime hora2) {
		return (horaACheckear.isAfter(hora1) && horaACheckear.isBefore(hora2));
	}

	public void setHoraInicio(LocalTime horaInicial) {
		horaInicio = horaInicial;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFinal) {
		horaFin = horaFinal;
	}

}
