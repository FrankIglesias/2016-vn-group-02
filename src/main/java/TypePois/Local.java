package TypePois;

import java.util.ArrayList;

import java.util.List;
import javax.persistence.*;

import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamTime.Feriado;
import DesignDreamTeamTime.HorarioYDia;

@Entity
public class Local extends POI{
	
	@ManyToOne
	private Rubro rubro;

	public Local(Geolocalizacion point, String nombre, HorarioYDia horario, Rubro rubro, List<Feriado> feriados) {
		super(point, nombre, new ArrayList<String>(), horario, feriados);
		this.rubro = rubro;
	}
	
	public Geolocalizacion getDireccion() { 
		return getPoint();
	}

	public boolean estasCercaDeUnPunto(Geolocalizacion point) {
		return rubro.estasCercaDeUnPunto(point, this);
	}
	
	public void setPalabrasClave(ArrayList<String> palabrasClave){
		super.setPalabrasClave(palabrasClave);
	}
}
