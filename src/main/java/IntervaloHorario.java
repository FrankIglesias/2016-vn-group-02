import java.time.LocalTime;

public class IntervaloHorario {

	LocalTime horaInicio;
	LocalTime horaFin;

	public IntervaloHorario(LocalTime horaInicial, LocalTime horaFinal) {
		super();
		horaInicio = horaInicial;
		horaFin = horaFinal;
	}

	public boolean incluyeHora(LocalTime hora) {
		return (hora.isAfter(horaInicio) && hora.isBefore(horaFin));
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
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
