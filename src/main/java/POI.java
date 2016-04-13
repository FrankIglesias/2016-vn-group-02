import java.util.ArrayList;
import java.util.GregorianCalendar;

public abstract class POI {

	protected Direccion direccion;
	protected String nombre;
	protected ArrayList<String> palabrasClave = new ArrayList<String>();
	protected Horario horario;

	public POI(Direccion dir, String nombre) {
		this.direccion = dir;
		this.nombre = nombre;
	}

	public abstract boolean estaDisponible(GregorianCalendar horario, Servicio servicio);

	public abstract boolean estaDisponible(GregorianCalendar horario);

	public double distanciaCon(POI unPoi) {
		return this.direccion.distanciaCon(unPoi.direccion);
	}
	
	public boolean estasCerca(Direccion unaDireccion) {
		return this.direccion.distanciaCon(unaDireccion)< 500;
	}

	public boolean tenesUnaPalabra(String unaPalabra) {
		return palabrasClave.stream().anyMatch(palabra -> palabra.equals(unaPalabra));
	}

	public void addPalabraClave(String unaPalabra) {
		this.palabrasClave.add(unaPalabra);
	}

	public static void main(String[] args) {

	}
	
}
