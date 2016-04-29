import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class POI {

	protected Geolocalizacion point;
	protected String nombre;
	protected ArrayList<String> palabrasClave = new ArrayList<String>();
	protected HorarioYDia horario = new HorarioYDia();
	protected List<Feriado> feriados;

	public POI(Geolocalizacion point, String nombre,
			ArrayList<String> palabrasClave, HorarioYDia horario,
			List<Feriado> feriados) {
		super();
		this.point = point;
		this.nombre = nombre;
		this.palabrasClave = palabrasClave;
		this.addPalabraClave(nombre);
		this.horario = horario;
		this.feriados = feriados;
	}

	public double distanciaConUnPOI(POI unPoi) {
		return this.point.distanciaCon(unPoi.point);
	}

	public boolean estasCercaDeUnPunto(Geolocalizacion point) {
		return distanciaMenor(this.point.distanciaCon(point), 500);
	}

	public boolean distanciaMenor(double distancia, double rango) {
		return distancia < rango;
	}

	public boolean tenesUnaPalabraDe(String unaFrase) {
		String[] listaDePalabrasDeFrase = unaFrase.split(" ");
		return palabrasClave.stream().anyMatch(
				palabra -> Arrays.asList(listaDePalabrasDeFrase).contains(
						palabra));

	}

	public void addPalabraClave(String unaPalabra) {
		this.palabrasClave.add(unaPalabra);
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
		return (Feriado) (feriados.stream().filter(unFeriado -> unFeriado.comparateConDiaYMes(fecha)));
	}


	public boolean tenesFeriados() {
		return (!feriados.isEmpty());
	}

	public boolean estaDisponible(LocalDateTime horarioPreguntado) {

		if (tenesFeriados() && sosFeriado(horarioPreguntado.toLocalDate()))
			return compararmeConHorarioDeUnFeriado(getUnFeriado(horarioPreguntado.toLocalDate()), horarioPreguntado);
		else

			return (horario.incluyeHorario(horarioPreguntado));

	}

}
