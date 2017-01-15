package TypePois;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamTime.Feriado;
import DesignDreamTeamTime.HorarioYDia;

@Entity

@Table(name = "Locales")
public class Local extends POI {

	@ManyToOne(cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "idRubro")
	private Rubro rubro;

	public Local(Geolocalizacion point, String nombre, HorarioYDia horario, Rubro rubro, List<Feriado> feriados) {
		super(point, nombre, new ArrayList<String>(), horario, feriados);
		this.rubro = rubro;
		this.addPalabrasClaves("Local");
	}

	public Local() {
	}

	public Geolocalizacion getDireccion() {
		return getPoint();
	}

	public boolean estasCercaDeUnPunto(Geolocalizacion point) {
		return rubro.estasCercaDeUnPunto(point, this);
	}

	public void setPalabrasClave(ArrayList<String> palabrasClave) {
		super.setPalabrasClave(palabrasClave);
	}
	public String getRubro(){
	return rubro.getNombre();
	}
	
	public void setRubro(Rubro rubro){
		this.rubro = rubro;
	}

	@Override
	public String getTipo() {
		return "local";
	}
}
