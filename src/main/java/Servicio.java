


import java.time.LocalDateTime;
import java.util.ArrayList;

public class Servicio {
	
		private HorarioYDia horario;
		private String nombreDelServicio;
		
		public Servicio(String nombre, HorarioYDia agenda){
			nombreDelServicio = nombre;
			horario = agenda;
		}

		public HorarioYDia getHorario() {
			return horario;
		}

		public void setHorario(HorarioYDia horario) {
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
