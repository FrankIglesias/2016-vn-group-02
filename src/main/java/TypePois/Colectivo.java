package TypePois;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamTime.Feriado;
import DesignDreamTeamTime.HorarioYDia;
import javax.persistence.*;
@Entity

public class Colectivo extends POI {
	
	@Column(name="linea_colectivo")
	private String linea;
	public Colectivo()
	{
		super();
	}
	
	public Colectivo(Geolocalizacion point, String nombre, ArrayList<String> palabrasClave, String linea,
			List<Feriado> feriados) {
		super(point, nombre, palabrasClave, new HorarioYDia(), feriados);
		this.linea = linea;
		this.addPalabrasClaves(linea);
		this.addPalabrasClaves("Colectivo");
	}

	
	public boolean estasCercaDeUnPunto(Geolocalizacion point) {
		return distanciaMenor(this.getPoint().distanciaCon(point), 100);
	}

	public boolean estaDisponible(LocalDateTime horario) {

		return true;
	}

	@Override
	public String getTipo() {
		
		return "Colectivo";
	}

}
