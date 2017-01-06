package DesignDreamTeamTime;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity

public class Feriado {

	@Id
	@GeneratedValue
	@Column(name="idFeriado")
	private int id;
	
	protected int mes;
	protected int dia;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	protected IntervaloHorario intervaloFeriado;

	public Feriado(int unMes, int unDia, IntervaloHorario unIntervalo) {
		this.mes = unMes;
		this.dia = unDia;
		this.intervaloFeriado = unIntervalo;
	}
	
	public Feriado() {
		
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

	public boolean incluisHorario(LocalDateTime horario) {
		return (this.intervaloFeriado.incluyeHora(horario));
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public IntervaloHorario getIntervaloFeriado() {
		return intervaloFeriado;
	}

	public void setIntervaloFeriado(IntervaloHorario intervaloFeriado) {
		this.intervaloFeriado = intervaloFeriado;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}
	
	

}
