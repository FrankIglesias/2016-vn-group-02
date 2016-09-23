package DesignDreamTeamErrorHandlers;

import org.quartz.Job;
import org.quartz.JobExecutionException;

public class ErrorEjecutarNVeces extends DDTErrorHandler {

	private int numeroFinal;
	int numeroDeVeces = 0;
	DDTErrorHandler accionDeError;

	public void setNumeroFinal(int numero) {
		numeroFinal = numero;
	}
	
	public void setAccionDeError(DDTErrorHandler accionDeError) {
		this.accionDeError = accionDeError;
	}
	
	public void ejecutarAccion(Job proceso, Exception e) throws JobExecutionException {
			super.ejecutarAccion(null, e);
			if (numeroDeVeces < numeroFinal) {
				numeroDeVeces++;
				proceso.execute(null);
				 }
			else
				accionDeError.ejecutarAccion(null, e);
		
	}

}
