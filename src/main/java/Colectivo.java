import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Colectivo extends POI {

	private String linea;

	public Colectivo(Geolocalizacion point, String nombre, ArrayList<String> palabrasClave, 
			String linea, List<Feriado> feriados) {
		super(point, nombre, palabrasClave, new HorarioYDia(), feriados);
		this.linea = linea;
		this.addPalabraClave(linea.toString());
	}

	public boolean estasCerca(Geolocalizacion point) {
		return this.point.distanciaCon(point) < 100;
	}

	@Override
	public boolean estaDisponible(LocalDateTime horario) {
		boolean retorno = false;
		
		if (tenesFeriados(horario.toLocalDate())) {
			Feriado feriado = (Feriado) this.feriados.stream().filter(Feriado -> Feriado.comparateConDiaYMes(horario.toLocalDate()));
			
			if(feriado.incluisHorario(horario.toLocalTime())) {
				retorno = true;
			}
		}
		else
			retorno = true;
		
		
		return retorno;
	}

}
