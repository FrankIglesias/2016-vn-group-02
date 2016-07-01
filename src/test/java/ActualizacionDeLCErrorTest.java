import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ActualizarLocalesComerciales.ActualizadorDeLC;
import Repositorio.RepoPOIs;

public class ActualizacionDeLCErrorTest {

	ActualizadorDeLC actualizador;
	RepoPOIs repo;
	
	@Before
	public void init(){
		actualizador = new ActualizadorDeLC(null, null);
		repo = RepoPOIs.getInstance();
		repo.inicializarPuntosDeIntereses();
		actualizador.setArchivoALevantar("LocalesComerciales2.txt");
	}
	
	@Test
	public void ActualizadorDeLCNoLograLevantarArchivoYNoHaceNada(){
		repo.addLocal("Carrousel", new ArrayList<String>());
		repo.addLocal("LoDeMari", new ArrayList<String>());
		actualizador.actualizarListaDeLC();
		ArrayList<String> palabrasClaves1 = actualizador.getPalabrasClavesDeLinea("Carrousel");
		ArrayList<String> palabrasClaves2 = actualizador.getPalabrasClavesDeLinea("LoDeMari");
		Assert.assertFalse(repo.tieneLasPalabrasClaves("LoDeMari", palabrasClaves2));
		Assert.assertFalse(repo.tieneLasPalabrasClaves("Carrousel", palabrasClaves1));
	}
	

}
