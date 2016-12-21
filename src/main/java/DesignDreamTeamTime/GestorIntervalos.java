package DesignDreamTeamTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity

public class GestorIntervalos {
	
	@Id
	@GeneratedValue
	public int id;
	
	
	@ElementCollection
	//@CollectionTable(name="intervalosHorarios", joinColumns=@JoinColumn(name="id_poi"))
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
