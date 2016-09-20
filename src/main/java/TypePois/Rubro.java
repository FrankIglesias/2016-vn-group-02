package TypePois;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import DesignDreamTeamLocation.Geolocalizacion;

@Entity
@Table(name="Rubro")
public class Rubro {

	@Id
	@GeneratedValue
	@Column(name="idRubro")
	private int idRubro;
	
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
