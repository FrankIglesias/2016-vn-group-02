import java.util.Date;

public class Colectivo extends POI {

	private int linea;
	
	public Colectivo(Direccion dir, String nombre) {
		super(dir, nombre);
	
	}

	@Override
	public boolean estaDisponible(Date horario, Servicio servicio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean estaDisponible(Date horario) {
		// TODO Auto-generated method stub
		return false;
	}

}
