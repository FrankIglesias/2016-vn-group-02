import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestActualizacionDeLC {
	
	ActualizadorDeLC actualizador;
	RepoLocalesComerciales repo;
	
	@Before
	public void init(){
		actualizador = new ActualizadorDeLC();
		repo = RepoLocalesComerciales.getInstance();
		repo.inicializarLocalesComerciales();
	}
	
	@Test
	public void RepoVacioTest() {
		Assert.assertTrue(repo.isEmpty());
	}
	
	@Test
	public void RepoSinLocalesComercialesEntoncesNoAgregaTest(){
		actualizador.actualizarListaDeLC(repo);
		Assert.assertEquals(repo.size(), 0);
	}
	
	@Test
	public void RepoActualizadoCorrectamenteTest(){
		repo.add("Carrousel", new ArrayList<String>());
		repo.add("LoDeMari", new ArrayList<String>());
		actualizador.actualizarListaDeLC(repo);
		Assert.assertEquals(repo.size(), 2);
	}
	
	@Test
	public void RepoActualizadoUnSoloLocalTest(){
		repo.add("LoDeMari", new ArrayList<String>());
		actualizador.actualizarListaDeLC(repo);
		Assert.assertEquals(repo.size(), 1);
		
	}
}
