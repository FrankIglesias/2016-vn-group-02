import java.util.Date;

public class Local extends POI {
	
	private String horario;
	private Rubro rubro;
	public Local(Direccion dir, String nombre) {
		super(dir, nombre);
		// TODO Auto-generated constructor stub
	}
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
