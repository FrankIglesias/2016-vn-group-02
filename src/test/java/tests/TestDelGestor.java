package tests;
import org.junit.Test;
import org.quartz.SchedulerException;

import ActualizarLocalesComerciales.ActualizadorMock;
import DesignDreamTeamProcesses.Gestor;

public class TestDelGestor {

	Gestor gestor = new Gestor();

	@Test
	public void agregarProcesoYCorrerloOK() throws SchedulerException, InterruptedException {
		ActualizadorMock actualizador = new ActualizadorMock();
		
		gestor.correrAgenda(actualizador);
		Thread.sleep(2000);
	}
}
