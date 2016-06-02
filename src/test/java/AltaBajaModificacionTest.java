import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AltaBajaModificacionTest {
	
	@Test
	public void testDeAltaDeDatos() {
		BaseDeDatos unaBase1 = new BaseDeDatos();
		Assert.assertEquals(unaBase1.cantidadDePOI(), 0);

	}

	@Test
	public void testDeAgregarPOI() {
		BaseDeDatos unaBase = new BaseDeDatos();
		unaBase.agregarNuevosPoi(GlobalTestVariables.crearUnColectivo());
		unaBase.agregarNuevosPoi(GlobalTestVariables.crearUnColectivo());
		unaBase.agregarNuevosPoi(GlobalTestVariables.crearUnColectivo());
		Assert.assertEquals(unaBase.cantidadDePOI(), 3);

	}

	@Test
	public void testDeQuitarPOI() {
		BaseDeDatos unaBase = new BaseDeDatos();
		unaBase.agregarNuevosPoi(GlobalTestVariables.crearUnColectivo());
		unaBase.agregarNuevosPoi(GlobalTestVariables.crearUnColectivo());
		unaBase.sacarPoi(GlobalTestVariables.crearUnColectivo());
		Assert.assertEquals(unaBase.cantidadDePOI(), 1);

	}
	@Test
	public void testAgregarBancosDelPoi() {
		BaseDeDatos unaBase = new BaseDeDatos();
		ApiDeBancoMock requesterFalso = new ApiDeBancoMock();
		unaBase.agregarNuevosPoi(GlobalTestVariables.crearUnColectivo());
		unaBase.agregarNuevosPoi(GlobalTestVariables.crearUnColectivo());
		unaBase.agregarVariosPoiDeListaDeBancos(requesterFalso.obtenerBancos());
		// de los bancos siempre devuelve 2 - 
		Assert.assertEquals(unaBase.cantidadDePOI(), 4);

	}


}
