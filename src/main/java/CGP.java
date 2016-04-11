import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CGP extends POI {
	public CGP(Direccion dir, String nombre) {
		super(dir, nombre);
		// TODO Auto-generated constructor stub
	}
	private List<Servicio> servicios = new ArrayList<Servicio>();
	
	
	
	
	public boolean estaDisponible(Date horario, Servicio servicio){
		Servicio servicioBuscado = buscarServicio( servicio);
		
		return (servicio.estasDisponibleEn(horario));
		}
	public boolean estaDisponible(Date horario){
		
		}

}
