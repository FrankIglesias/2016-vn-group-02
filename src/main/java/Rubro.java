import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Rubro {
	
	private Dia inicioDeSemana;
	private Dia finDeSemana;
	private int horaInicial;
	private int horaFinal;
	private String nombre;
	
	public Rubro(String name,Dia diaInicial, Dia diaFinal, int horaI, int horaF){
		super();
		inicioDeSemana = diaInicial;
		finDeSemana = diaFinal;
		horaFinal = horaF;
		horaInicial = horaI;	
		nombre = name;
	}
	
	
	public boolean estaDisponible(Date horario) {
		Calendar calendar = GregorianCalendar.getInstance(); 
		calendar.setTime(horario); 
		Integer hora = calendar.get(Calendar.HOUR_OF_DAY); 
		Integer dia = calendar.get(Calendar.DAY_OF_WEEK);
	// ojo que repite codigo en banco
		
		return false;
	
	}

}
