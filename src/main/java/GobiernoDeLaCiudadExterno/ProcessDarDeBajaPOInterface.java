package GobiernoDeLaCiudadExterno;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public interface ProcessDarDeBajaPOInterface {
	public void execute(JobExecutionContext arg0) throws JobExecutionException;
}
