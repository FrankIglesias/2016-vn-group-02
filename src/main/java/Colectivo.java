import java.time.LocalDateTime;
import java.util.ArrayList;

public class Colectivo extends POI {

	private String linea;

	public Colectivo(Geolocalizacion point, String nombre, ArrayList<String> palabrasClave, 
			String linea) {
		super(point, nombre, palabrasClave, new ArrayList<Horario>());
		this.linea = linea;
		this.addPalabraClave(linea.toString());
	}

	public boolean estasCerca(Geolocalizacion point) {
		return this.point.distanciaCon(point) < 100;
	}

	@Override
	public boolean estaDisponible(LocalDateTime horario) {
		return true;
	}

}
