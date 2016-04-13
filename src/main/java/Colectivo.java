import java.util.GregorianCalendar;

public class Colectivo extends POI {

	private int linea;
	
	public Colectivo(Direccion dir, String nombre, int numero) {
		super(dir, nombre);
		linea = numero;
		}


	public boolean estaDisponible(GregorianCalendar horario, Servicio servicio) {
		return true;
	}
	public boolean estasCerca(Direccion unaDireccion) {
		return this.direccion.distanciaCon(unaDireccion)< 100;
	}

	public boolean estaDisponible(GregorianCalendar horario) {
		return true;
	}

}
