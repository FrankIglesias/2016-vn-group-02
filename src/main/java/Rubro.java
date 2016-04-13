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
	
	public boolean estaDisponible(GregorianCalendar horario) {
		return horario.estaEntreLosHorarios();
	}
	
	public boolean estasCerca(Direccion unaDireccion){
		return true;
	}

}
