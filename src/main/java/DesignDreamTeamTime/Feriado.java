package DesignDreamTeamTime;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	public String getMesString(){
		switch(mes){
		case 1:
			return "Enero";
		case 2:
			return "Febrero";
		case 3:
			return "Marzo";
		case 4:
			return "Abril";
		case 5:
			return "Mayo";
		case 6:
			return "Junio";
		case 7:
			return "Julio";
		case 8:
			return "Agosto";
		case 9:
			return "Septiembre";
		case 10:
			return "Octubre";
		case 11:
			return "Noviembre";
		case 12:
			return "Diciembre";
		default:
			return "";
		}
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
