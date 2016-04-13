

import java.util.Calendar;
import java.util.GregorianCalendar;


public class Horario {

	private int inicioDeSemana;
	private int finDeSemana;
	private int horaInicial;
	private int horaFinal;
	
	public Horario (int inicioSem,int finSem,int horai, int horaf){
		super();
		this.horaFinal = horaf;
		this.horaInicial = horai;
		this.inicioDeSemana = inicioSem;
		this.finDeSemana = finSem;
	}
	public boolean estaEntreLosHorarios(GregorianCalendar horarioPreguntado){
		int diaPreguntado = horarioPreguntado.get(Calendar.DAY_OF_WEEK);
		int horaPreguntada = horarioPreguntado.get(Calendar.HOUR_OF_DAY);
		if((inicioDeSemana <= diaPreguntado && diaPreguntado <= finDeSemana))
		return ((horaInicial <= horaPreguntada) && (horaPreguntada <= horaFinal));
		return false;
		
}
}