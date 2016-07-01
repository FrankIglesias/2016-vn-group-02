import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ActualizarLocalesComerciales.ActualizadorDeLC;
import Repositorio.RepoPOIs;

public class TestActualizacionDeLC {
	
	ActualizadorDeLC actualizador;
	RepoPOIs repo;
	
	@Before
	public void init(){
		actualizador = new ActualizadorDeLC(null, null);
		repo = RepoPOIs.getInstance();
		repo.inicializarPuntosDeIntereses();
		actualizador.setArchivoALevantar("LocalesComerciales.txt");
	}
	
	@Test
	public void RepoVacioTest() {
		Assert.assertTrue(repo.isEmpty());
	}
	
	@Test
	public void RepoSinLocalesComercialesEntoncesNoAgregaTest(){
		actualizador.actualizarListaDeLC();
		Assert.assertEquals(repo.size(), 0);
	}
	
	@Test
	public void RepoActualizadoCorrectamenteTest(){
		repo.addLocal("Carrousel", new ArrayList<String>());
		repo.addLocal("LoDeMari", new ArrayList<String>());
		actualizador.actualizarListaDeLC();
		ArrayList<String> palabrasClaves1 = actualizador.getPalabrasClavesDeLinea("Carrousel");
		ArrayList<String> palabrasClaves2 = actualizador.getPalabrasClavesDeLinea("LoDeMari");
		Assert.assertTrue(repo.tieneLasPalabrasClaves("LoDeMari", palabrasClaves2));
		Assert.assertFalse(repo.tieneLasPalabrasClaves("Carrousel", palabrasClaves1));
	}
	
	@Test
	public void RepoActualizadoUnSoloLocalTest(){
		repo.addLocal("LoDeMari", new ArrayList<String>());
		actualizador.actualizarListaDeLC();
		ArrayList<String> palabrasClaves2 = actualizador.getPalabrasClavesDeLinea("LoDeMari");
		Assert.assertTrue(repo.tieneLasPalabrasClaves("LoDeMari", palabrasClaves2));
	}


}
