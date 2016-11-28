package DesignDreamTeamErrorHandlers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionException;


public abstract class DDTErrorHandler {
	
	List<ErrorLog> logDeErrores = new ArrayList<ErrorLog>();
	
	public void guardarError(Exception e) {
		ErrorLog error = new ErrorLog(LocalDate.now(), 1, e.toString());
		logDeErrores.add(error);
	}
	
	public List<ErrorLog> getLogDeErrores() {
		return logDeErrores;
	}
	
	public void ejecutarAccion(Job proceso, Exception e) throws JobExecutionException {
		guardarError(e);
		return;
	}
}
