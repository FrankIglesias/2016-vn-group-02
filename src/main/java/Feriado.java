import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;


public class Feriado {
	
	protected Month mes;
	protected int dia;
	protected IntervaloHorario intervaloFeriado;


public boolean comparateConDiaYMes(LocalDate unaFecha)
{
	
	return (dia == unaFecha.getDayOfYear() && mes == unaFecha.getMonth());
}

public boolean incluisHorario(LocalTime horario)
{
	return(this.intervaloFeriado.incluyeHora(horario));
}



}
