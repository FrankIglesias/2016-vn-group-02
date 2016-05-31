import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AltaBajaModificacionTest {
	private static BaseDeDatos unaBase = new BaseDeDatos();

	@Test
	public void testDeAltaDeDatos() {
		unaBase.agregarNuevosPoi(GlobalTestVariables.crearUnColectivo());
		unaBase.agregarNuevosPoi(GlobalTestVariables.crearUnColectivo());

		Assert.assertEquals(unaBase.cantidadDePOI(), 2);

	}

	@Test
	public void testDeAgregarPOI() {
		unaBase.agregarNuevosPoi(GlobalTestVariables.crearUnColectivo());
		unaBase.agregarNuevosPoi(GlobalTestVariables.crearUnColectivo());
		unaBase.agregarNuevosPoi(GlobalTestVariables.crearUnColectivo());
		Assert.assertEquals(unaBase.cantidadDePOI(), 3);

	}

	@Test
	public void testDeQuitarPOI() {
		unaBase.agregarNuevosPoi(GlobalTestVariables.crearUnColectivo());
		unaBase.agregarNuevosPoi(GlobalTestVariables.crearUnColectivo());
		unaBase.sacarPoi(GlobalTestVariables.crearUnColectivo());
		Assert.assertEquals(unaBase.cantidadDePOI(), 1);

	}

}
