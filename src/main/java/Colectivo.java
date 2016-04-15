import java.time.LocalDateTime;

public class Colectivo extends POI {

	private Integer linea;

	public Colectivo(Direccion dir, String nombre, int numero) {
		super(dir, nombre);
		linea = numero;
		this.addPalabraClave(linea.toString());
	}

	public boolean estasCerca(Direccion unaDireccion) {
		return this.direccion.distanciaCon(unaDireccion) < 100;
	}

	@Override
	public boolean estaDisponible(LocalDateTime horario) {
		return true;
	}

}
