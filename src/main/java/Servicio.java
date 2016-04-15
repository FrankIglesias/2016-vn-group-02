


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Servicio {
	
		private ArrayList<Horario> horario;
		private String nombreDelServicio;
		public Servicio(String nombre, ArrayList<Horario> horario){
			nombreDelServicio = nombre;
			horario = horario;
		}
	
//		public boolean estasDisponibleEn(LocalDateTime horarioConsultado) {
//			return horario.estaDisponibleEnHorario(horarioConsultado);
//			
//		}
		public String getNombre()
		{
			return nombreDelServicio;
		}
}
