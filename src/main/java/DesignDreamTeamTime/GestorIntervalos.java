package DesignDreamTeamTime;

import java.util.ArrayList;

public class GestorIntervalos {

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
