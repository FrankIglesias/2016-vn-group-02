import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ActualizarLocalesComerciales.ActualizadorDeLC;
import BancoExterno.ApiDeBancoMock;
import DesignDreamTeamErrorHandlers.ErrorEnviarMailAlAdministrador;
import DesignDreamTeamErrorHandlers.DDTErrorHandler;
import DesignDreamTeamProcesses.GestorDeProcesos;
import Repositorios.RepoPOIs;
import Repositorios.Terminal;

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
		// TODO
	}

}
