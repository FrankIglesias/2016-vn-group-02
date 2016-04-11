import java.util.Date;

public class Colectivo extends POI {

	private int linea;
	
	public Colectivo(Direccion dir, String nombre) {
		super(dir, nombre);
		}

	public boolean estaDisponible(Date horario, Servicio servicio) {
		return true;
	}


	public boolean estaDisponible(Date horario) {
		return true;
	}

}
