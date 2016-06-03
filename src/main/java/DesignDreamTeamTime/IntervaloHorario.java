package DesignDreamTeamTime;

import java.time.LocalTime;

public class IntervaloHorario {

	LocalTime horaInicio;
	LocalTime horaFin;
	LocalTime mediaNoche = LocalTime.MIDNIGHT;

	public IntervaloHorario(LocalTime horaInicial, LocalTime horaFinal) {
		super();
		horaInicio = horaInicial;
		horaFin = horaFinal;
	}

	public boolean incluyeHora(LocalTime hora) {
		if (horaFin.isBefore(horaInicio))
			return (beetwen(hora, horaInicio, mediaNoche) || beetwen(hora, mediaNoche, horaFin));

		return (beetwen(hora, horaInicio, horaFin));

	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	private boolean beetwen(LocalTime horaACheckear, LocalTime hora1, LocalTime hora2) {
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
