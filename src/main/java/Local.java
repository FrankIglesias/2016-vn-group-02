import java.util.ArrayList;

public class Local extends POI {

	private Rubro rubro;

	public Local(Geolocalizacion point, String nombre, ArrayList<Horario> horario,
			Rubro rubro) {
		super(point, nombre, new ArrayList<String>(), horario);
		this.rubro = rubro;
	}

	public Geolocalizacion getDireccion() {
		return point;
	}

	
// A pesar de que al constructor super() le faltan parametros, está medio mal tener estos dos,
// es mejor crear el rubro afuera y despues pasarselo
	
//	public Local(String nombre, Geolocalizacion point, Rubro rubro) { // en caso
//																		// que
//																		// el
//		// // rubro
//		// exista
//		super(point, nombre);
//		this.rubro = rubro;
//	}
//
//	public Local(String nombreDelRubro, Geolocalizacion point, String nombre, int radioDeCercania) {
//		super(point, nombre);
//		rubro = new Rubro(nombreDelRubro, radioDeCercania);
//		addPalabraClave(nombreDelRubro);
//	}

	public boolean estasCerca(Geolocalizacion point) {
		return rubro.estasCerca(point, this);
	}
}
