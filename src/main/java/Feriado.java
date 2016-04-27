import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;


public class Feriado {
	
	protected int mes;
	protected int dia;
	protected IntervaloHorario intervaloFeriado;


public Feriado(int unMes, int unDia, IntervaloHorario unIntervalo)
{
	this.mes = unMes;
	this.dia = unDia;
	this.intervaloFeriado = unIntervalo;
}
	
public boolean comparateConDiaYMes(LocalDate unaFecha)
{
	return (dia == unaFecha.getDayOfMonth() && mes == unaFecha.getMonthValue());
}

public boolean incluisHorario(LocalTime horario)
{
	return(this.intervaloFeriado.incluyeHora(horario));
}



}