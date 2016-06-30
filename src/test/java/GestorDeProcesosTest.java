import java.util.Date;

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
