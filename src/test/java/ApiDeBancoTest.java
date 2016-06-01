import org.junit.Assert;
import org.junit.Test;
public class ApiDeBancoTest {

	ApiDeBanco requester = new ApiDeBancoReal();
	ApiDeBancoMock secondRequester = new ApiDeBancoMock();

	@Test
	public void testDeObtenerStreamDesdePagina() throws Exception {
		String json = requester.obtenerStream();
		Assert.assertTrue(json.contains("de"));

	}

	@Test
	public void testObtenerBancoDesdeReal() throws Exception {
		Banco unBanco = requester.obtenerBancos();
		Assert.assertEquals(unBanco.nombre, "Banco de la Plaza");

	}

	@Test
	public void testDeObtenerUnBancoObjetoDeMockDeString() throws Exception {
		Banco unBanco = secondRequester.obtenerBancos();
		Assert.assertEquals(unBanco.nombre, "Banco de la Plaza");

	}
	@Test
	public void testDeObtenerUnBancoObjetoDeMockDeArchivo() throws Exception {
		secondRequester.setRuta();
		Banco unBanco = secondRequester.obtenerBancoDesdeArchivo();
		Assert.assertEquals(unBanco.nombre, "Banco de la Plaza");

	}

}