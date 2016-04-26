import java.util.ArrayList;
import java.util.List;

public class Local extends POI {

	private Rubro rubro;

	public Local(Geolocalizacion point, String nombre, HorarioYDia horario, Rubro rubro, List<Feriado> feriados) {
		super(point, nombre, new ArrayList<String>(), horario, feriados);
		this.rubro = rubro;
	}

	public Geolocalizacion getDireccion() {
		return point;
	}

	public boolean estasCercaDeUnPunto(Geolocalizacion point) {
		return rubro.estasCercaDeUnPunto(point, this);
	}
}
