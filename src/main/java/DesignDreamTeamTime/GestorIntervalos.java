package DesignDreamTeamTime;
import javax.persistence.*;
import java.util.ArrayList;

@Entity

public class GestorIntervalos {
	
	@Id
	@Column(name="id_gestor")
	private int id;
	
	
	/*@ElementCollection
	@CollectionTable(name="intervalosHorarios", joinColumns=@JoinColumn(name="id_poi"))*/
	@Transient
	private ArrayList<IntervaloHorario> intervalosHorarios = new ArrayList<IntervaloHorario> ();

	public ArrayList<IntervaloHorario> getIntervalosHorarios() {
		return intervalosHorarios;
	}

	public void setIntervalosHorarios(ArrayList<IntervaloHorario> intervalosHorarios) {
		this.intervalosHorarios = intervalosHorarios;
	}
	
	public GestorIntervalos(ArrayList<IntervaloHorario> intervalos)
	{
		super();
		this.intervalosHorarios = intervalos;
	}
	
	
}
