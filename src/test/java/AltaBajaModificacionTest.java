import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import BancoExterno.ApiDeBancoMock;
import Repositorio.RepoPOIs;


public class AltaBajaModificacionTest {
	
	@Test
	public void testDeAltaDeDatos() {
		RepoPOIs unaBase = RepoPOIs.getInstance();
		unaBase.inicializarPuntosDeIntereses();
		Assert.assertEquals(unaBase.cantidadDePOI(), 0);

	}

	@Test
	public void testDeAgregarPOI() {
		RepoPOIs unaBase = RepoPOIs.getInstance();
		unaBase.inicializarPuntosDeIntereses();
		unaBase.agregarNuevosPoi(GlobalTestVariables.crearUnColectivo());
		unaBase.agregarNuevosPoi(GlobalTestVariables.crearUnColectivo());
		unaBase.agregarNuevosPoi(GlobalTestVariables.crearUnColectivo());
		Assert.assertEquals(unaBase.cantidadDePOI(), 3);

	}

	@Test
	public void testDeQuitarPOI() {
		RepoPOIs unaBase = RepoPOIs.getInstance();
		unaBase.inicializarPuntosDeIntereses();
		unaBase.agregarNuevosPoi(GlobalTestVariables.crearUnColectivo());
		unaBase.agregarNuevosPoi(GlobalTestVariables.crearUnColectivo());
		unaBase.sacarPoi(GlobalTestVariables.crearUnColectivo());
		Assert.assertEquals(unaBase.cantidadDePOI(), 1);

	}
	@Test
	public void testAgregarBancosDelPoi() {
		RepoPOIs unaBase = RepoPOIs.getInstance();
		unaBase.inicializarPuntosDeIntereses();
		ApiDeBancoMock requesterFalso = new ApiDeBancoMock();
		unaBase.agregarNuevosPoi(GlobalTestVariables.crearUnColectivo());
		unaBase.agregarNuevosPoi(GlobalTestVariables.crearUnColectivo());
		unaBase.agregarVariosPoiDeListaDeBancos(requesterFalso.obtenerBancoDesdeString());
		// de los bancos siempre devuelve 2 - 
		Assert.assertEquals(unaBase.cantidadDePOI(), 4);

	}


}
