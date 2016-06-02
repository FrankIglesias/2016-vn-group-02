package MainClasses;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamTime.Feriado;
import DesignDreamTeamTime.HorarioYDia;

public abstract class POI {

	private Geolocalizacion point;
	private String nombre;
	private ArrayList<String> palabrasClave = new ArrayList<String>();
	protected HorarioYDia horario = new HorarioYDia();
	protected List<Feriado> feriados;

	public POI(Geolocalizacion point, String nombre,
			ArrayList<String> palabrasClave, HorarioYDia horario, List<Feriado> feriados) {
		super();
		this.setPoint(point);
		this.setNombre(nombre);
		this.setPalabrasClave(palabrasClave);
		this.addPalabraClave(nombre);
		this.horario = horario;
		this.feriados = feriados;
	}

	public double distanciaConUnPOI(POI unPoi) {
		return this.getPoint().distanciaCon(unPoi.getPoint());
	}

	public boolean estasCercaDeUnPunto(Geolocalizacion point) {
		return distanciaMenor(this.getPoint().distanciaCon(point), 500);
	}

	public boolean distanciaMenor(double distancia, double rango) {
		return distancia < rango;
	}

	public boolean tenesUnaPalabraDe(String unaFrase) {
		String[] listaDePalabrasDeFrase = unaFrase.split(" ");
		return getPalabrasClave().stream().anyMatch(
				palabra -> Arrays.asList(listaDePalabrasDeFrase).contains(
						palabra));

	}

	public void addPalabraClave(String unaPalabra) {
		this.getPalabrasClave().add(unaPalabra);
	}

	public void addFeriado(Feriado unFeriado) {
		this.feriados.add(unFeriado);
	}

	public boolean sosFeriado(LocalDate fecha) {
		return (feriados.stream().anyMatch(unFeriado -> unFeriado
				.comparateConDiaYMes(fecha)));
	}

	public boolean compararmeConHorarioDeUnFeriado(Feriado unFeriado, LocalDateTime horario) {
		return (unFeriado.incluisHorario(horario.toLocalTime()));
	}
	public Feriado getUnFeriado(LocalDate fecha)
	{
		return (feriados.stream().filter(unFeriado -> unFeriado.comparateConDiaYMes(fecha))).collect(Collectors.toList()).get(0);
	}


	public boolean tenesFeriados() {
		return (!feriados.isEmpty());
	}

	public boolean estaDisponible(LocalDateTime horarioPreguntado) {

		if (tenesFeriados() && sosFeriado(horarioPreguntado.toLocalDate())){
			return compararmeConHorarioDeUnFeriado(getUnFeriado(horarioPreguntado.toLocalDate()), horarioPreguntado);
		}
		else
			return (horario.incluyeHorario(horarioPreguntado));

	}

	public Geolocalizacion getPoint() {
		return point;
	}

	public void setPoint(Geolocalizacion point) {
		this.point = point;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<String> getPalabrasClave() {
		return palabrasClave;
	}

	public void setPalabrasClave(ArrayList<String> palabrasClave) {
		this.palabrasClave = palabrasClave;
	}

}