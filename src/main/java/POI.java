
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class POI {

	protected Geolocalizacion point;
	protected String nombre;
	protected ArrayList<String> palabrasClave = new ArrayList<String>();
	protected ArrayList<Horario> horario = new ArrayList<Horario>();

	public POI(Geolocalizacion p, String nom) {
		point = p;
		nombre = nom;
		this.addPalabraClave(nombre);
	}

	public double distanciaCon(POI unPoi) {
		return this.point.distanciaCon(unPoi.point);
	}

	public boolean estasCerca(Geolocalizacion point) {
		return this.point.distanciaCon(point) < 500;
	}

	public boolean tenesUnaPalabraDe(String unaFrase) {
		String[] split = unaFrase.split(" ");
		return palabrasClave.stream().anyMatch(palabra -> Arrays.asList(split).contains(palabra));

	}

	public void addPalabraClave(String unaPalabra) {
		this.palabrasClave.add(unaPalabra);
	}

	public boolean estaDisponible(LocalDateTime horarioPreguntado) {

		return horario.stream().anyMatch(unHorario -> unHorario.incluyeHorario(horarioPreguntado));

	}

}
