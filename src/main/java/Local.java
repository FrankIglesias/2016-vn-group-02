
public class Local extends POI {

	private Rubro rubro;
	private String nombre;

	public Local(String nombreDelRubro, Geolocalizacion point, String nombre, int radioDeCercania) {
		super(point, nombre);
		rubro = new Rubro(nombreDelRubro, radioDeCercania);
		addPalabraClave(nombreDelRubro);
	}

	public Geolocalizacion getDireccion() {
		return point;
	}

	public Local(String nombre, Geolocalizacion point, Rubro rubro) { // en caso
																		// que
																		// el
		// // rubro
		// exista
		super(point, nombre);
		this.rubro = rubro;
	}

	public boolean estasCerca(Geolocalizacion point) {
		return rubro.estasCerca(point, this);
	}
}
