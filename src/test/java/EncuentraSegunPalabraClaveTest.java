

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EncuentraSegunPalabraClaveTest {
	 
	public Direccion direccionBanco;
	public Banco banco;
	public Direccion direccionColectivo;
	public Colectivo colectivo;
	public List<POI> puntosDeInteres = new ArrayList<POI>();
	public Sistema sistPrueba;
	
	@Before 
	public void init(){  
		sistPrueba = new Sistema();
		
		direccionBanco = new Direccion(150, 160,12);
		banco = new Banco(direccionBanco, "Banco");
		banco.addPalabraClave("dinero");
		banco.addPalabraClave("cuenta");
		banco.addPalabraClave("banco");
		banco.addPalabraClave("banelco");
		
		direccionColectivo = new Direccion(130, 140,13);
		colectivo = new Colectivo(direccionColectivo, "Colectivo",193);
		colectivo.addPalabraClave("colectivo");
		colectivo.addPalabraClave("transporte");
		colectivo.addPalabraClave("publico");
		colectivo.addPalabraClave("rueditas");
		
		puntosDeInteres.add(colectivo);
		puntosDeInteres.add(banco);
		
	}
	@Test
	public void encontrarPOISegunPalabra() {
		String fraseABuscar = "quiero un banco malo";
		Sistema.setList(puntosDeInteres);
		Sistema.buscarSegunPalabraClave(fraseABuscar);
		
		Assert.assertTrue(Sistema.buscarSegunPalabraClave(fraseABuscar).contains(banco)); 
		Assert.assertEquals("Cantidad de elementos en el array",1,Sistema.buscarSegunPalabraClave("banelco").size());
		
		
	}
	
	
}
