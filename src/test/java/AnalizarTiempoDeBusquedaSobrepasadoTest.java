import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnalizarTiempoDeBusquedaSobrepasadoTest {

	Buscador buscador2 = new Buscador();
	List<MainClasses.POI> lista2 = new ArrayList<MainClasses.POI>();
	Terminal terminal = new Terminal("TerminalPepito");
	
	@Before
	public void init() {
		
		for(int i=0; i<500000; i++){
			lista2.add(GlobalTestVariables.crearUnBanco(null));
		}
		buscador2.setTiempoMaximoDeBusqueda(0.001);
		Buscador.setPuntosDeIntereses(lista2);

	}
	
	@Test
	public void testTiempoDeBusquedaMaximoSobrepasado() {
		Assert.assertEquals(terminal.getGestor().getContadorDeMails(), 1);
	}
	
}
