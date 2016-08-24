package DesignDreamTeamProcesses;

import java.util.Date;

import DesignDreamTeamErrorHandlers.DDTErrorHandler;

public abstract class DesignDreamTeamProcess implements Runnable {
	protected Date date;
	protected DDTErrorHandler AccionDeError;

	public DesignDreamTeamProcess(DDTErrorHandler accion, Date date) {
		this.AccionDeError = accion;
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

}
