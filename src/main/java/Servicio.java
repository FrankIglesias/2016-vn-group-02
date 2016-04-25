
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Servicio {

	private ArrayList<Horario> horario;
	private String nombreDelServicio;

	public Servicio(String nombre, ArrayList<Horario> listaDeHorarios) {
		nombreDelServicio = nombre;
		horario = listaDeHorarios;
	}

	public ArrayList<Horario> getHorario() {
		return horario;
	}

	public void setHorario(ArrayList<Horario> horario) {
		this.horario = horario;
	}

	public String getNombreDelServicio() {
		return nombreDelServicio;
	}

	public void setNombreDelServicio(String nombreDelServicio) {
		this.nombreDelServicio = nombreDelServicio;
	}

	public String getNombre() {
		return nombreDelServicio;
	}

	public boolean estaDisponible(LocalDateTime unHorario) {
		return horario.stream().anyMatch(hora -> hora.incluyeHorario(unHorario));

	}
}
