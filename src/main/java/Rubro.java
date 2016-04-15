
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Rubro {
	
	private ArrayList<Horario> horario;
	private String nombre;
	private int radioDeCercania;
	
	public Rubro(String name,ArrayList<Horario> horario,int radioDeCercania){
		super();
		this.horario = horario;	
		this.nombre = name;
		this.radioDeCercania = radioDeCercania;
	}
	
//	public boolean estaDisponible(GregorianCalendar horario) {
//		return horario.estaDisponibleEnHorario(horario);
//	}
	
	public boolean estasCerca(Direccion unaDireccion, Local miLocal){
		return miLocal.getDireccion().distanciaCon(unaDireccion) < radioDeCercania;
	}

}
