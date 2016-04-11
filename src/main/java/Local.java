import java.util.Date;

public class Local extends POI {
	
	private String horario;
	private Rubro rubro;
	
	public Local(Direccion dir, String nombre) {
		super(dir, nombre);
	}
	
	public boolean estaDisponible(Date horario, Servicio servicio) {
		return 	this.estaDisponible(horario);
	}
	public boolean estaDisponible(Date horario) {
		return rubro.estaDisponible(horario);
	}
}
