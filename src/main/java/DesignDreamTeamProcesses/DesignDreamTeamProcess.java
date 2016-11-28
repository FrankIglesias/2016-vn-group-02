package DesignDreamTeamProcesses;

import java.util.Date;


import org.quartz.Job;

import DesignDreamTeamErrorHandlers.DDTErrorHandler;

public abstract class DesignDreamTeamProcess implements Job {
	protected Date date;
	protected DDTErrorHandler AccionDeError;

	public void setAccionDeError(DDTErrorHandler accion) {
		this.AccionDeError = accion;	
	}
}
