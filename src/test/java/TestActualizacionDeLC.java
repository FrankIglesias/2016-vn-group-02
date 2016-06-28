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
		
	}
	
	@Test
	public void RepoVacioTest() {
		Assert.assertTrue(repo.isEmpty());
	}
	
	@Test
	public void RepoActualizadoCorrectamenteTest(){
		actualizador.actualizarListaDeLC(repo);
		Assert.assertEquals(repo.size(), 2);
	}
}
