
import java.util.GregorianCalendar;

public class Servicio {
	
		private Horario horarioDelServicio;
		private String nombreDelServicio;
		public Servicio(String nombre, Horario horario){
			nombreDelServicio = nombre;
			horarioDelServicio = horario;
		}
	
		public boolean estasDisponibleEn(GregorianCalendar horario) {
			return horarioDelServicio.estaEntreLosHorarios(horario);
			
		}
		public String getNombre()
		{
			return nombreDelServicio;
		}
}
