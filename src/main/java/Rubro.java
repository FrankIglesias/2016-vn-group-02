import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Rubro {
	
	private Horario horario;
	private String nombre;
	
	public Rubro(String name,Horario horario){
		super();
		this.horario = horario;	
		nombre = name;
	}
	
	
	public boolean estaDisponible(Date horario) {
		Calendar calendar = GregorianCalendar.getInstance(); 
		calendar.setTime(horario); 
		Integer hora = calendar.get(Calendar.HOUR_OF_DAY); 
		Integer dia = calendar.get(Calendar.DAY_OF_WEEK);
	// ojo que repite codigo en banco
	// no esta actualizado al manejo del atributo horario.
		
		return false;
	
	}

}
