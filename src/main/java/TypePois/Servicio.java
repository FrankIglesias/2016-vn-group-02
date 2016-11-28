package TypePois;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import DesignDreamTeamTime.Feriado;
import DesignDreamTeamTime.HorarioYDia;

@Entity

public class Servicio {

	@Id
	@GeneratedValue
	@Column(name="idServicio")
	private int id;
	
	
	@OneToOne(cascade = CascadeType.PERSIST)
	private HorarioYDia horario;
	
	
	private String nombreDelServicio;

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name="idServicio")
	protected List<Feriado> feriados;

	public Servicio(String nombre, HorarioYDia agenda, List<Feriado> feriados) {
		nombreDelServicio = nombre;
		horario = agenda;
		this.feriados = feriados;
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

	public String getNombre() {
		return nombreDelServicio;
	}

	public boolean sosFeriado(LocalDate fecha) {
		return (feriados.stream().anyMatch(unFeriado -> unFeriado.comparateConDiaYMes(fecha)));
	}

	public boolean compararmeConHorarioDeUnFeriado(Feriado unFeriado, LocalDateTime horario) {
		return (unFeriado.incluisHorario(horario.toLocalTime()));
	}

	public Feriado getUnFeriado(LocalDate fecha) {
		return (feriados.stream().filter(unFeriado -> unFeriado.comparateConDiaYMes(fecha)))
				.collect(Collectors.toList()).get(0);
	}

	public boolean tenesFeriados() {
		return (!feriados.isEmpty());
	}

	public boolean estaDisponible(LocalDateTime horarioPreguntado) {
		if (tenesFeriados() && sosFeriado(horarioPreguntado.toLocalDate())) {
			return compararmeConHorarioDeUnFeriado(getUnFeriado(horarioPreguntado.toLocalDate()), horarioPreguntado);
		} else
			return (horario.incluyeHorario(horarioPreguntado));

	}
}
