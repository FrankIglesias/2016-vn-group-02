package DesignDreamTeamProcesses;

import org.apache.log4j.BasicConfigurator;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


public class Gestor {

	Scheduler sched;

	public void crearAgenda() throws SchedulerException {

	}

	public void correrAgenda(Job proceso) throws SchedulerException {

		// Carga las configuraciones para que algo que se llama log ande,
		// averiguar que es eso
		BasicConfigurator.configure();

		Scheduler sched = new StdSchedulerFactory().getScheduler();

		sched.start();
		JobDetail job = JobBuilder.newJob(proceso.getClass()).withIdentity("SimpleJob").build();

		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("SimpleJob").startNow().build();
		sched.scheduleJob(job, trigger);
		
		sched.start();
		sched.resumeAll();
	}
}
