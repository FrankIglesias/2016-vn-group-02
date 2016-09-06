package ActualizarLocalesComerciales;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ActualizadorMock implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Corri la Actualizacion de Locales Comerciales");
		
	}

}
