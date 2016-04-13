import java.util.GregorianCalendar;

public class Local extends POI {

	private Rubro rubro;
	private String nombre;
	private Horario horario;

	public Local(String nombreDelRubro, Direccion dir, String nombre, int diaInicial, int diaFinal, int horaInicial,
			int horaFinal, int radioDeCercania) {
		super(dir, nombre);
		horario = new Horario(diaInicial, diaFinal, horaInicial, horaFinal);
		rubro = new Rubro(nombreDelRubro, horario, radioDeCercania);
	}

	public Direccion getDireccion(){
		return direccion;
	}
	public Local(String nombre, Direccion dir, Rubro rubro) { // en caso que el													// rubro exista
		super(dir, nombre);
		this.rubro = rubro;
	}

	public boolean estaDisponible(GregorianCalendar horario, Servicio servicio) {
		return this.estaDisponible(horario);
	}

	public boolean estaDisponible(GregorianCalendar horario) {
		return rubro.estaDisponible(horario);
	}

	public boolean estasCerca(Direccion unaDireccion) {
		return rubro.estasCerca(unaDireccion, this);
	}
}
