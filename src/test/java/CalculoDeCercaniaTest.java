
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculoDeCercaniaTest extends PoiMainTest {

	@Before
	public void init() {

		super.init();

	}

	/*
	 * Todo testeado de aca 
	 * http://www.movable-type.co.uk/scripts/latlong.html
	 */

	@Test
	public void calcularDistanciaEntreDosPOI() {

		Assert.assertEquals(banco.distanciaConUnPOI(centroDeCGP), 440, 100);
	}

	@Test
	public void calculoCercaniaConColectivo() {
		Assert.assertTrue(colectivo.estasCercaDeUnPunto(geolocalizacionPersonaCercanaColectivo));
	}

	@Test
	public void calculoCercaniaConCGP() {
		Assert.assertTrue(centroDeCGP.estasCercaDeUnPunto(geolocalizacionPersonaCercanaCGP));
	}

	@Test
	public void calculoCercaniaConLocal() {
		Assert.assertTrue(unLocal.estasCercaDeUnPunto(geolocalizacionPersonaCercanaLocal));
	}

	@Test
	public void calculoLejaniaConColectivo() {
		Assert.assertFalse(colectivo.estasCercaDeUnPunto(geolocalizacionPersonaLejosDeTodo));
	}

	@Test
	public void calculoLejaniaConCGP() {
		Assert.assertFalse(centroDeCGP.estasCercaDeUnPunto(geolocalizacionPersonaLejosDeTodo));
	}

	@Test
	public void calculoLejaniaConLocal() {
		Assert.assertFalse(unLocal.estasCercaDeUnPunto(geolocalizacionPersonaLejosDeTodo));
	}
}
