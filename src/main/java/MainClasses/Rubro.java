package MainClasses;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import DesignDreamTeamLocation.Geolocalizacion;

public class Rubro {

	private String nombre;
	private int radioDeCercania;

	public Rubro(String name, int radioDeCercania) {
		super();
		this.nombre = name;
		this.radioDeCercania = radioDeCercania;
	}

	public boolean estasCercaDeUnPunto(Geolocalizacion point, Local miLocal) {
		return miLocal.getDireccion().distanciaCon(point) < radioDeCercania;
	}

}
