package DesignDreamTeamProcesses;

import java.util.Date;
import java.util.TimerTask;

import DesignDreamTeamErrors.ErrorHandler;

public class DesignDreamTeamProcess extends TimerTask {
	Date date;
	ErrorHandler AccionDeError;

	public DesignDreamTeamProcess(ErrorHandler accion, Date date) {
		this.AccionDeError = accion;
		this.date = date;
	}
	public Date getDate() {
		return date;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
