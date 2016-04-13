import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Rubro {
	
	private Horario horarioDelRubro;
	private String nombre;
	
	public Rubro(String name,Horario horario){
		super();
		this.horarioDelRubro = horario;	
		nombre = name;
	}
	
	public boolean estaDisponible(GregorianCalendar horario) {
		return horarioDelRubro.estaEntreLosHorarios(horario);
	}
	
	public boolean estasCerca(Direccion unaDireccion){
		return true;
	}

}
