package tests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.quartz.JobExecutionException;

import ActualizarLocalesComerciales.ActualizadorMock;
import DesignDreamTeamErrorHandlers.ErrorEjecutarNVeces;
import DesignDreamTeamErrorHandlers.ErrorEnviarMailAlAdministrador;
import GestorDeMail.GestorDeMailTrucho;

public class DeteccionDeErrorTest {

	ActualizadorMock actualizador;
	ErrorEnviarMailAlAdministrador accionDeErrorDePrueba  = new ErrorEnviarMailAlAdministrador();
	ErrorEjecutarNVeces accionEjecutarNveces  = new ErrorEjecutarNVeces();
	GestorDeMailTrucho gestor = GestorDeMailTrucho.getInstance();
	
	@Before
	public void init() {
		actualizador = new ActualizadorMock();
		actualizador.habilitarError();
	}

	@Test
	public void testDeErrorHandlerMandarUnMailAlAdministrador() throws JobExecutionException {
		actualizador.setAccionDeError(accionDeErrorDePrueba);
		actualizador.execute(null);
		Assert.assertEquals(gestor.getContadorDeMails(), 1);
	}
	
	@Test
	public void testDeErrorHandlerReintentar2VecesNoPuedeYMandaMail() throws JobExecutionException {
		accionEjecutarNveces.setNumeroFinal(2);
		accionEjecutarNveces.setAccionDeError(accionDeErrorDePrueba);
		actualizador.setAccionDeError(accionEjecutarNveces);
		actualizador.execute(null);
		Assert.assertEquals(gestor.getContadorDeMails(), 1);
	}

}
