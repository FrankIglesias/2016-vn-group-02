import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CGP extends POI {
	private List<Servicio> servicios = new ArrayList<Servicio>();
	
	
	
	
	public boolean estaDisponible(Date horario, Servicio servicio){
		Servicio servicioBuscado = buscarServicio( servicio);
		
		return (servicio.estasDisponibleEn(horario));
		}
	public boolean estaDisponible(Date horario){
		
		}

}
