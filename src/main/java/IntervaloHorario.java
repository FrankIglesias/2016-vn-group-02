import java.time.LocalTime;

public class IntervaloHorario {

	LocalTime horaInicio;
	LocalTime horaFin;
	
	
	public LocalTime getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}
	public LocalTime getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}
	public IntervaloHorario(LocalTime horaInicio, LocalTime horaFin) {
		super();
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}
	
	public boolean incluyeHora(LocalTime hora) {
		boolean retorno = false;
		
		if(hora.isAfter(this.horaInicio) && hora.isBefore(horaFin)) {
			retorno = true;
		}
		
		return retorno;
	}
	
}
