
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Rubro {
	
	private String nombre;
	private int radioDeCercania;
	
	public Rubro(String name,int radioDeCercania){
		super();
		this.nombre = name;
		this.radioDeCercania = radioDeCercania;
	}
	
	public boolean estasCerca(Direccion unaDireccion, Local miLocal){
		return miLocal.getDireccion().distanciaCon(unaDireccion) < radioDeCercania;
	}

}
