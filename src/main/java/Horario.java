import java.util.Calendar;
import java.util.GregorianCalendar;

public class Horario {

	private int inicioDeSemana;
	private int finDeSemana;
	private int horaInicial;
	private int horaFinal;
	
	public Horario (int horai, int horaf){
		super();
		this.horaFinal = horaf;
		this.horaInicial = horai;
	}
	public Horario (int inicioSem,int finSem,int horai, int horaf){
		super();
		this.horaFinal = horaf;
		this.horaInicial = horai;
		this.inicioDeSemana = inicioSem;
		this.finDeSemana = finSem;
	}
	public boolean estaEntreLosHorarios(GregorianCalendar horarioPreguntado){
		int dia = horarioPreguntado.get(Calendar.DAY_OF_WEEK);
		Integer hora = horarioPreguntado.get(Calendar.HOUR_OF_DAY);
		if((horaInicial <= hora && hora <= horaFinal))
				return (inicioDeSemana <= dia && dia <= finDeSemana);
		return false;
	}
}