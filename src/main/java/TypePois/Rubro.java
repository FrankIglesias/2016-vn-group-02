package TypePois;

import javax.persistence.Entity;
import javax.persistence.Id;

import DesignDreamTeamLocation.Geolocalizacion;

@Entity
public class Rubro {

	@Id
	public long idRubro;
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
