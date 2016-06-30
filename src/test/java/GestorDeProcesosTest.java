import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ActualizarLocalesComerciales.ActualizadorDeLC;
import Repositorio.ActualizadorDeRepositorioDePoi;
import Repositorio.GestorDeProcesos;
import Repositorio.RepoPOIs;

public class GestorDeProcesosTest {

	GestorDeProcesos gestor;
	ActualizadorDeLC actualizador;
	RepoPOIs repo;
	DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date;
	static Semaphore sem = new Semaphore(0);

	@Before
	public void init() {
		gestor = new GestorDeProcesos();
		actualizador = new ActualizadorDeLC();
		repo = RepoPOIs.getInstance();
		repo.inicializarPuntosDeIntereses();
	}

	@Test
	public void actualizacionProgramadaYConcretadaTest() {
		gestor.setProceso(new ActualizadorDeRepositorioDePoi(), new Date());
		Assert.assertTrue(repo.size() > 0);
	}

}
