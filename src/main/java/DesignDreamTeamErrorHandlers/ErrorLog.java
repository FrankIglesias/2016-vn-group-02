package DesignDreamTeamErrorHandlers;

import java.time.LocalDate;


public class ErrorLog {
	
	public LocalDate fecha;
	public int elementosAfectados;
	public String resultadoEjecuccion;
	
	public ErrorLog(LocalDate fecha, int elementosAfectados, String resultadoEjecuccion) {
		this.fecha = fecha;
		this.elementosAfectados = elementosAfectados;
		this.resultadoEjecuccion = resultadoEjecuccion;
	}
	
	

}
