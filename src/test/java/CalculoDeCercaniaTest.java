

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculoDeCercaniaTest extends PoiMainTest {
	 /*
	  * direccion1 = new Direccion(-34.596044,-58.419946,2);
		direccion2 = new Direccion(-34.602945,-58.420948,2);
		
		*/

	
	@Before 
	public void init(){  
	
		super.init();
		
	
	}
	@Test
	public void calcularDistanciaEntreDosPOI() {
		/*http://www.movable-type.co.uk/scripts/latlong.html
		 * testeado de aca*/
		Assert.assertEquals(banco.distanciaCon(centroDeCGP),440,10);
	}
	
	
}
