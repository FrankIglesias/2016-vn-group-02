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
		
		direccionBanco = new Direccion(150, 160);
		banco = new Banco(direccionBanco, "Banco");
		banco.setPalabrasClaves("dinero");
		banco.setPalabrasClaves("cuenta");
		banco.setPalabrasClaves("banco");
		banco.setPalabrasClaves("banelco");
		
		direccionColectivo = new Direccion(130, 140);
		colectivo = new Colectivo(direccionColectivo, "Colectivo");
		colectivo.setPalabrasClaves("colectivo");
		colectivo.setPalabrasClaves("transporte");
		colectivo.setPalabrasClaves("publico");
		colectivo.setPalabrasClaves("rueditas");
		
		puntosDeInteres.add(colectivo);
		puntosDeInteres.add(banco);
		
	}
	@Test
	public void encontrarPOISegunPalabra() {
		Sistema.setList(puntosDeInteres);
		Sistema.buscarSegunPalabraClave("banelco");
		
		Assert.assertTrue(Sistema.buscarSegunPalabraClave("banelco").contains(banco));  
		
	}
	
	
}
