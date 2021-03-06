package DesignDreamTeamTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


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
	
	public GestorIntervalos()
	{
		
	}
	
	public void setIntervalosHorarios(List<IntervaloHorario> intervalosHorarios) {
		this.intervalosHorarios = intervalosHorarios;
	}
	
	public GestorIntervalos(List<IntervaloHorario> intervalos)
	{
		super();
		this.intervalosHorarios = intervalos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
