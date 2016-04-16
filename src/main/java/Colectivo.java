import java.time.LocalDateTime;

public class Colectivo extends POI {

	private Integer linea;

	public Colectivo(Geolocalizacion point, String nombre, int numero) {
		super(point, nombre);
		linea = numero;
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
