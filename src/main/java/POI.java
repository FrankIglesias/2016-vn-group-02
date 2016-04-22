
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class POI {

	protected Geolocalizacion point;
	protected String nombre;
	protected ArrayList<String> palabrasClave = new ArrayList<String>();
	protected ArrayList<Horario> horario = new ArrayList<Horario>();
	protected List<Feriado> feriados;
	

	public POI(Geolocalizacion point, String nombre, ArrayList<String> palabrasClave, ArrayList<Horario> horario, List<Feriado> feriados) {
		super();
		this.point = point;
		this.nombre = nombre;
		this.palabrasClave = palabrasClave;
		this.addPalabraClave(nombre);
		this.horario = horario;
		this.feriados = feriados;
	}

	public double distanciaCon(POI unPoi) {
		return this.point.distanciaCon(unPoi.point);
	}

	public boolean estasCerca(Geolocalizacion point) {
		return this.point.distanciaCon(point) < 500;
	}

	public boolean tenesUnaPalabraDe(String unaFrase) {
		String[] listaDePalabrasDeFrase = unaFrase.split(" ");
		return palabrasClave.stream().anyMatch(palabra -> Arrays.asList(listaDePalabrasDeFrase).contains(palabra));

	}

	public void addPalabraClave(String unaPalabra) {
		this.palabrasClave.add(unaPalabra);
	}
	
	public void addFeriado(Feriado unFeriado)
	{
		this.feriados.add(unFeriado);
	}
	
	
	public boolean compararmeConFeriados(LocalDate fecha)
	{
		return (feriados.stream().anyMatch(unFeriado -> unFeriado.comparateConDiaYMes(fecha)));
	}

	

	public boolean estoyEnFeriado(LocalDate fecha) {
		boolean retorno = false;

		if (this.feriados.size() > 0) { 
			if(this.compararmeConFeriados(fecha))
				retorno = true;
		}

		return retorno;
	}
 
	public boolean estaDisponible(LocalDateTime horarioPreguntado) {
		boolean retorno = false;

		if (estoyEnFeriado(horarioPreguntado.toLocalDate())) {
			Feriado fecha = (Feriado) this.feriados.stream().filter(Feriado -> Feriado.comparateConDiaYMes(horarioPreguntado.toLocalDate()));
			
			if(fecha.incluisHorario(horarioPreguntado.toLocalTime())) {
				retorno = true;
			} 
		}
		else if(horario.stream().anyMatch(unHorario -> unHorario.incluyeHorario(horarioPreguntado))) {
			retorno = true;
		}

		return retorno;
	}

}
