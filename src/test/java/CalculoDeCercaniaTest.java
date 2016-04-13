import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculoDeCercaniaTest {
	 
	public Colectivo colectivo;
	public Banco banco;
	public Direccion direccion1;
	public Direccion direccion2;
	
	@Before 
	public void init(){  
	
		direccion1 = new Direccion(9961/180,151/90);
		direccion2 = new Direccion(9961/180,377/225);
		colectivo = new Colectivo(direccion1,"134",46);
		banco = new Banco(direccion2,"Banelco");
		
	}
	@Test
	public void calcularDistanciaEntreDosPIO() {
		
		Assert.assertEquals(colectivo.distanciaCon(banco),equals(150));
	}
	
	
}
