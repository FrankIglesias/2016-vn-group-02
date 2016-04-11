import java.util.Date;

public class Local extends POI {
	
		private Rubro rubro;
		private String nombre; 
		
	public Local(String nombreDelRubro ,Direccion dir, String nombre, Dia diaInicial, Dia diaFinal, int horaInicial, int horaFinal) {
		super(dir, nombre);
		rubro = new Rubro(nombreDelRubro,diaInicial, diaFinal, horaInicial, horaFinal);
	}
	
	public Local(String nombre, Direccion dir ,Rubro rubro) { // en caso que el rubro exista
		super(dir, nombre);
		this.rubro = rubro;
	}
	
	public boolean estaDisponible(Date horario, Servicio servicio) {
		return 	this.estaDisponible(horario);
	}
	public boolean estaDisponible(Date horario) {
		return rubro.estaDisponible(horario);
	}

}
