

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
	String fraseABuscar;
	
	
	@Before 
	public void init(){  
		buscador = new Buscador();
		banco = crearUnBanco();
		colectivo = crearUnColectivo();
		
		fraseABuscar = "quiero un banco malo";
		puntosDeInteres.add(colectivo);
		puntosDeInteres.add(banco);
		
		Buscador.setPuntosDeIntereses(puntosDeInteres);
		Buscador.buscarSegunPalabraClave(fraseABuscar);
		
	}
	@Test
	public void encontrarPOISegunPalabra() {
		
		
		Assert.assertTrue(Buscador.buscarSegunPalabraClave(fraseABuscar).contains(banco)); 
		Assert.assertEquals("Cantidad de elementos en el array",1,Buscador.buscarSegunPalabraClave("banelco").size());
	}
	
	@Test
	public void noEncuentraPOISegunPalabraClave()
	{
		Assert.assertFalse(Buscador.buscarSegunPalabraClave(fraseABuscar).contains(colectivo));
	}
	
	
}
