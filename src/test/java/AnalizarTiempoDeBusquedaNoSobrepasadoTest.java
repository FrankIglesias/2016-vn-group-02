import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class AnalizarTiempoDeBusquedaNoSobrepasadoTest {

	Buscador buscador1 = new Buscador();
	Terminal terminal = new Terminal("TerminalPepito");

	@Before
	public void init() {
		List<MainClasses.POI> lista = new ArrayList<MainClasses.POI>();
		lista.add(GlobalTestVariables.crearUnBanco(null));
		buscador1.setTiempoMaximoDeBusqueda(5);
		Buscador.setPuntosDeIntereses(lista);

	}

	@Test
	public void testTiempoDeBusquedaMaximoNoSobrepasado() {
		Assert.assertEquals(terminal.getGestor().getContadorDeMails(), 0);
	}
	
	
}
