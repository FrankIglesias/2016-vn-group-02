package MainClasses;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;

import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamTime.Feriado;
import DesignDreamTeamTime.HorarioYDia;

public class CGP extends POI {
	private List<Servicio> servicios = new ArrayList<Servicio>();

	public CGP(Geolocalizacion point, String nombre, List<Servicio> servicios, List<Feriado> feriados) {
		super(point, nombre, new ArrayList<String>(), new HorarioYDia(), feriados);
		this.servicios = servicios;
		servicios.stream().forEach(servicio -> addPalabraClave(servicio.getNombre()));
		
	}

	public boolean estasCercaDeUnPunto(Geolocalizacion otroPoint) {
		return distanciaMenor(getPoint().distanciaCon(otroPoint), 500);
	}

	public Servicio buscarServicio(String nombreDelServicio) {
		Servicio elServicioBuscado = servicios.stream()
				.filter(unServicio -> unServicio.getNombre().equals(nombreDelServicio)).findFirst().get();
		return elServicioBuscado;

	}

	public boolean estaDisponible(LocalDateTime horario) {
		
		return servicios.stream().anyMatch(unServicio -> unServicio.estaDisponible(horario));
	}
}
