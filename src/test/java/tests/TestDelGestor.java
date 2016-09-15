package tests;
import org.junit.Test;
import org.quartz.SchedulerException;

import DesignDreamTeamProcesses.Gestor;

public class TestDelGestor {

	Gestor gestor = new Gestor();

	@Test
	public void agregarProcesoYCorrerloOK() throws SchedulerException, InterruptedException {
		gestor.correrAgenda();
		Thread.sleep(2000);
	}
}
