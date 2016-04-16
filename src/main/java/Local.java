
public class Local extends POI {

	private Rubro rubro;
	private String nombre;

	public Local(String nombreDelRubro, Direccion dir, String nombre, int radioDeCercania) {
		super(dir, nombre);
		rubro = new Rubro(nombreDelRubro, radioDeCercania);
		addPalabraClave(nombreDelRubro);
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public Local(String nombre, Direccion dir, Rubro rubro) { // en caso que el
																// // rubro
																// exista
		super(dir, nombre);
		this.rubro = rubro;
	}

	public boolean estasCerca(Direccion unaDireccion) {
		return rubro.estasCerca(unaDireccion, this);
	}
}
