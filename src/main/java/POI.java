import java.util.ArrayList;
import java.util.Date;

public abstract class POI {

	protected Direccion direccion;
	protected String nombre;
	protected ArrayList<String> palabrasClave = new ArrayList<String>();

	public POI(Direccion dir, String nombre) {
		this.direccion = dir;
		this.nombre = nombre;
	}

	public abstract boolean estaDisponible(Date horario, Servicio servicio);

	public abstract boolean estaDisponible(Date horario);

	public double distanciaCon(POI unPoi) {
		return this.direccion.distanciaCon(unPoi.direccion);
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
