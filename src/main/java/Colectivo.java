import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Colectivo extends POI {

	private String linea;

	public Colectivo(Geolocalizacion point, String nombre, ArrayList<String> palabrasClave, 
			String linea, List<Feriado> feriados) {
		super(point, nombre, palabrasClave, new HorarioYDia(),feriados);
		this.linea = linea;
		this.addPalabraClave(linea);
	}

	public boolean estasCercaDeUnPunto(Geolocalizacion point) {
		return distanciaMenor(this.point.distanciaCon(point), 100);
	}

	
	public boolean estaDisponible(LocalDateTime horario) {

		return true;
	}

}
