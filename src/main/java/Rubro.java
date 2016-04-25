public class Rubro {

	private String nombre;
	private int radioDeCercania;

	public Rubro(String name, int radioDeCercania) {
		super();
		this.nombre = name;
		this.radioDeCercania = radioDeCercania;
	}

	public boolean estasCerca(Geolocalizacion point, Local miLocal) {
		return miLocal.getDireccion().distanciaCon(point) < radioDeCercania;
	}

}
