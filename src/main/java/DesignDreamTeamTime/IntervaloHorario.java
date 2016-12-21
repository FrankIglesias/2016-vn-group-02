package DesignDreamTeamTime;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.uqbarproject.jpa.java8.extras.convert.LocalDateTimeConverter;

@Entity
public class IntervaloHorario {

	@Id
	@GeneratedValue
	public int id;
	
	@Convert(converter = LocalDateTimeConverter.class)
	public LocalDateTime horaInicio;

	@Convert(converter = LocalDateTimeConverter.class)
	public LocalDateTime horaFin;
	
	@Transient
	LocalDateTime mediaNoche; //TODO armar una medianoche
	
	
	public IntervaloHorario(LocalDateTime horaInicial, LocalDateTime horaFinal) {
		super();
		horaInicio = horaInicial;
		horaFin = horaFinal;
	
	}
	
	public boolean incluyeHora(LocalDateTime hora) {
		if (horaInicio.toLocalTime() == hora.toLocalTime()){
			return true;
		} else if(horaFin == hora){
			return false;
		}
		if (horaFin.isBefore(horaInicio))
			return (between(hora, horaInicio, mediaNoche) || between(hora, mediaNoche, horaFin));

		return (between(hora, horaInicio, horaFin));

	}

	public LocalTime getHoraInicio() {
		return horaInicio.toLocalTime();
	}

	private boolean between(LocalDateTime horaACheckear, LocalDateTime hora1, LocalDateTime hora2) {
		return (horaACheckear.toLocalTime().isAfter(hora1.toLocalTime()) && horaACheckear.toLocalTime().isBefore(hora2.toLocalTime()));
	}

	public void setHoraInicio(LocalDateTime horaInicial) {
		horaInicio = horaInicial;
	}

	public LocalTime getHoraFin() {
		return horaFin.toLocalTime();
	}

	public void setHoraFin(LocalDateTime horaFinal) {
		horaFin = horaFinal;
	}

	public void setId(int id) {
		this.id = id;
	}
}
