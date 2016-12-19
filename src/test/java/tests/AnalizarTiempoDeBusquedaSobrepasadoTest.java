package tests;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Repositorios.Buscador;
import Repositorios.Terminal;

public class AnalizarTiempoDeBusquedaSobrepasadoTest {

	Buscador buscador2 = new Buscador();
	List<TypePois.POI> lista2 = new ArrayList<TypePois.POI>();
	Terminal terminal = new Terminal("TerminalPepito");
	
	
	@Before
	public void init() {
		
		for(int i=0; i<5000; i++){
			lista2.add(GlobalTestVariables.crearUnBanco(null));
		}
		buscador2.setTiempoMaximoDeBusqueda(0.01);
		Buscador.setPuntosDeIntereses(lista2);
		buscador2.buscarPoisMongo("banelco", terminal);
	}
	
	@Test
	public void testTiempoDeBusquedaMaximoSobrepasado() {
		Assert.assertEquals(terminal.getGestor().getContadorDeMails(), 1);
	}
	
}
