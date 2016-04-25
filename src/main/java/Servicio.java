


import java.time.LocalDateTime;
import java.util.ArrayList;

public class Servicio {
	
		private Horario horario;
		private String nombreDelServicio;
		
		public Servicio(String nombre, Horario agenda){
			nombreDelServicio = nombre;
			horario = agenda;
		}

		public Horario getHorario() {
			return horario;
		}

		public void setHorario(Horario horario) {
			this.horario = horario;
		}

		public String getNombreDelServicio() {
			return nombreDelServicio;
		}

		public void setNombreDelServicio(String nombreDelServicio) {
			this.nombreDelServicio = nombreDelServicio;
		}
		public String getNombre()
		{
			return nombreDelServicio;
		}
		
		public boolean estaDisponible(LocalDateTime unHorario) {
			return horario.incluyeHorario(unHorario);
			
		}
}
