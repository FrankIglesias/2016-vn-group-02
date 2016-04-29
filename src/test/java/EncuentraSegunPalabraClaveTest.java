

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EncuentraSegunPalabraClaveTest extends GlobalTestVariables{
	
	public Banco banco;
	public Colectivo colectivo;
	public List<POI> puntosDeInteres = new ArrayList<POI>();
	public Buscador buscador;
	
	
	@Before 
	public void init(){  
		buscador = new Buscador();
		banco = crearUnBanco();
		colectivo = crearUnColectivo();
		
		puntosDeInteres.add(colectivo);
		puntosDeInteres.add(banco);
		
	}
	@Test
	public void encontrarPOISegunPalabra() {
		String fraseABuscar = "quiero un banco malo";
		Buscador.setPuntosDeIntereses(puntosDeInteres);
		Buscador.buscarSegunPalabraClave(fraseABuscar);
		
		Assert.assertTrue(Buscador.buscarSegunPalabraClave(fraseABuscar).contains(banco)); 
		Assert.assertEquals("Cantidad de elementos en el array",1,Buscador.buscarSegunPalabraClave("banelco").size());
	}
	
	
	
}
