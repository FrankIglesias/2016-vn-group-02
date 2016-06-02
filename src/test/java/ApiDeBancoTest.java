import java.util.List;

import org.junit.Assert;
import org.junit.Test;
public class ApiDeBancoTest {

	ApiDeBancoReal requester = new ApiDeBancoReal();
	ApiDeBancoMock secondRequester = new ApiDeBancoMock();

	@Test
	public void testDeObtenerStreamDesdePagina() throws Exception {
		String json = requester.obtenerStream();
		Assert.assertTrue(json.contains("de"));

	}

	@Test
	public void testObtenerBancoDesdeReal() throws Exception {
		List<Banco> listaDeBancos  = requester.obtenerBancos();
		Assert.assertTrue(listaDeBancos.size()==2);

	}

	@Test
	public void testDeObtenerBancosDesdeString() throws Exception {
		List<Banco> listaDeBancos = secondRequester.obtenerBancos();
		Assert.assertTrue(listaDeBancos.size()==2);

	}
	@Test
	public void testDeObtenerUnBancoObjetoDeMockDeArchivo() throws Exception {
		secondRequester.setRuta();
		Banco unBanco = secondRequester.obtenerBancoDesdeArchivo();
		Assert.assertEquals(unBanco.nombre, "Banco de la Plaza");

	}
	@Test
	public void testDeObtenerUnBancoObjetoDeMockDeArchivoContieneUnServicio() throws Exception {
		secondRequester.setRuta();
		Banco unBanco = secondRequester.obtenerBancoDesdeArchivo();
		Assert.assertTrue(unBanco.palabrasClave.contains("cobro cheques"));
	}

}