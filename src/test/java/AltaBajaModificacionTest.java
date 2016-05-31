import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AltaBajaModificacionTest {
	private BaseDeDatos unaBase = new BaseDeDatos();

	@Before
	public void init() {

		unaBase.agregarNuevosPoi(GlobalTestVariables.crearUnColectivo());
		unaBase.agregarNuevosPoi(GlobalTestVariables.crearUnColectivo());
	}

	@Test
	public void testDeAltaDeDatos() {
		Assert.assertEquals(unaBase.cantidadDePOI(), 2);

	}
	@Test
	public void testDeAgregarPOI() {
		unaBase.agregarNuevosPoi(GlobalTestVariables.crearUnColectivo());
		Assert.assertEquals(unaBase.cantidadDePOI(), 3);

	}
	@Test
	public void testDeQuitarPOI() {
		unaBase.sacarPoi(GlobalTestVariables.crearUnColectivo());
		Assert.assertEquals(unaBase.cantidadDePOI(), 1);

	}

}
