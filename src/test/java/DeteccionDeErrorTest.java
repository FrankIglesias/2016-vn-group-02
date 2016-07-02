import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ActualizarLocalesComerciales.ActualizadorDeLC;
import BancoExterno.ApiDeBancoMock;
import DesignDreamTeamErrors.ErrorEnviarMailAlAdministrador;
import DesignDreamTeamErrors.ErrorHandler;
import DesignDreamTeamProcesses.GestorDeProcesos;
import Repositorio.ActualizadorDeRepositorioDePoi;
import Repositorio.RepoPOIs;
import Repositorio.Terminal;

public class DeteccionDeErrorTest {

	GestorDeProcesos gestor;
	ActualizadorDeLC actualizador;
	RepoPOIs repo;
	DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	int numero = 0;
	Date date;
	Date date1;
	Terminal terminal = new Terminal("TerminalPepito");
	ErrorEnviarMailAlAdministrador accionDeErrorDePrueba  = new ErrorEnviarMailAlAdministrador();
	@Before
	public void init() {
		gestor = new GestorDeProcesos();
		actualizador = new ActualizadorDeLC(null, date);
		repo = RepoPOIs.getInstance();
		repo.inicializarPuntosDeIntereses();
		numero = repo.size();
		repo.addLocal("LoDeMari", new ArrayList<String>());
		
		// faltarian los error handler
	}

	@Test
	public void testDeErrorHandlerMandarUnMailAlAdministrador() {
		date = gestor.setFecha("2016-06-30 01:58:00");
		ActualizadorDeRepositorioDePoi actualizadorFalluto  =new ActualizadorDeRepositorioDePoi(accionDeErrorDePrueba, date);
		ApiDeBancoMock.setRutaDeArchivo("juaniGato.txt");
		actualizadorFalluto.setAccionDeError(accionDeErrorDePrueba);
		
		gestor.setProceso(actualizadorFalluto);
		gestor.correrProcesos();
		Assert.assertEquals(terminal.getGestor().getContadorDeMails(), 0);

	}

}
