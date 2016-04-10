import java.util.ArrayList;
import java.util.List;

public abstract class POI {
protected Direccion direccion;
protected String nombre; 
protected List palabrasClave = new ArrayList<String>();

	public POI(Direccion dir,String nombre){
		this.Direccion = dir;
		this.nombre = nombre;
		}

}
