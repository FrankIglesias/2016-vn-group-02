package src.main.java;
import java.util.GregorianCalendar;

public class Rubro {
	
	private Horario horarioDelRubro;
	private String nombre;
	private int radioDeCercania;
	
	public Rubro(String name,Horario horario,int radioDeCercania){
		super();
		this.horarioDelRubro = horario;	
		this.nombre = name;
		this.radioDeCercania = radioDeCercania;
	}
	
	public boolean estaDisponible(GregorianCalendar horario) {
		return horarioDelRubro.estaEntreLosHorarios(horario);
	}
	
	public boolean estasCerca(Direccion unaDireccion, Local miLocal){
		return miLocal.getDireccion().distanciaCon(unaDireccion) < radioDeCercania;
	}

}
