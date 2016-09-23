package ActualizarLocalesComerciales;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import DesignDreamTeamErrorHandlers.DDTErrorHandler;
import DesignDreamTeamProcesses.DesignDreamTeamProcess;

public class ActualizadorMock extends DesignDreamTeamProcess {

	DDTErrorHandler accionDeError;
	Boolean error = false;
	
	
	public void setAccionDeError(DDTErrorHandler accionDeError) {
		this.accionDeError = accionDeError;
	}
	
	public void habilitarError() {
		error = true;
	}
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
		if(!error)
		System.out.println("Corri la Actualizacion de Locales Comerciales");
		else
			throw new RuntimeException(); }
		catch (RuntimeException e) {
			accionDeError.ejecutarAccion(this, e);
		}
	}

}
