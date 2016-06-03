package MainClasses;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamTime.Feriado;
import DesignDreamTeamTime.HorarioYDia;

public class Colectivo extends POI {

	private String linea;

	public Colectivo(Geolocalizacion point, String nombre, ArrayList<String> palabrasClave, 
			String linea, List<Feriado> feriados) {
		super(point, nombre, palabrasClave, new HorarioYDia(),feriados);
		this.linea = linea;
		this.addPalabrasClaves(linea);
	}

	public boolean estasCercaDeUnPunto(Geolocalizacion point) {
		return distanciaMenor(this.getPoint().distanciaCon(point), 100);
	}

	
	public boolean estaDisponible(LocalDateTime horario) {

		return true;
	}

}
