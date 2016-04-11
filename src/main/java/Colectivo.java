import java.util.Date;

public class Colectivo extends POI {

	private String linea;
	
	public Colectivo(Direccion dir, String nombre) {
		super(dir, nombre);
	}

	public boolean estaDisponible(Date horario, Servicio servicio) {
		return true;
	}

	@Override
	public boolean estaDisponible(Date horario) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
