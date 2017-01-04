package DesignDreamTeamTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
public class GestorIntervalos {
	
	@Id
	@GeneratedValue
	public int id;
	

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	public List<IntervaloHorario> intervalosHorarios = new ArrayList<IntervaloHorario> ();

	public List<IntervaloHorario> getIntervalosHorarios() {
		return intervalosHorarios;
	}

	public void setIntervalosHorarios(List<IntervaloHorario> intervalosHorarios) {
		this.intervalosHorarios = intervalosHorarios;
	}
	
	public GestorIntervalos(List<IntervaloHorario> intervalos)
	{
		super();
		this.intervalosHorarios = intervalos;
	}
	
	
}
