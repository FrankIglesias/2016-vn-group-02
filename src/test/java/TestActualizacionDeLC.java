import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ActualizarLocalesComerciales.ActualizadorDeLC;
import Repositorio.RepoLocalesComerciales;
import Repositorio.RepoPOIs;

public class TestActualizacionDeLC {
	
	ActualizadorDeLC actualizador;
	RepoPOIs repo;
	
	@Before
	public void init(){
		actualizador = new ActualizadorDeLC();
		repo = RepoPOIs.getInstance();
		repo.inicializarPuntosDeIntereses();
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
		Assert.assertEquals(repo.size(), 2);
	}
	
	@Test
	public void RepoActualizadoUnSoloLocalTest(){
		repo.addLocal("LoDeMari", new ArrayList<String>());
		actualizador.actualizarListaDeLC();
		Assert.assertEquals(repo.size(), 1);
	}

}
