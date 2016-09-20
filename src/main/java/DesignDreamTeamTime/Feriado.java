package DesignDreamTeamTime;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

@Entity
public class Feriado {

	@Id
	@GeneratedValue
	@Column(name="idFeriado")
	private int id;
	
	protected int mes;
	protected int dia;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	protected IntervaloHorario intervaloFeriado;

	public Feriado(int unMes, int unDia, IntervaloHorario unIntervalo) {
		this.mes = unMes;
		this.dia = unDia;
		this.intervaloFeriado = unIntervalo;
	}

	public int getId()
	{
		return this.id;
	}
	public int getMes()
	{
		return this.mes;
	}
	public boolean comparateConDiaYMes(LocalDate unaFecha) {
		return (dia == unaFecha.getDayOfMonth() && mes == unaFecha.getMonthValue());
	}

	public boolean incluisHorario(LocalTime horario) {
		return (this.intervaloFeriado.incluyeHora(horario));
	}

}
