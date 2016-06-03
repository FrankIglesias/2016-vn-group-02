import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import BancoExterno.ApiDeBancoMock;
import BancoExterno.ApiDeBancoReal;
import BancoExterno.BancoTrucho;
import BancoExterno.JsonFactory;
import TypePois.Banco;
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
		Assert.assertEquals(unBanco.getNombre(), "Banco de la Plaza");

	}
	@Test
	public void testDeObtenerUnBancoObjetoDeMockDeArchivoContieneUnServicio() throws Exception {
		secondRequester.setRuta();
		Banco unBanco = secondRequester.obtenerBancoDesdeArchivo();
		Assert.assertTrue(unBanco.getPalabrasClave().contains("cobro cheques"));
	}

	@Test
	public void testParsearYObtenerUnBanco() {
		 String jsonStudent = "{" + "\"banco\": \"Banco de la Plaza\"," + "\"x\": -35.9338322," + "\"y\": 72.348353,"
				+ "\"sucursal\": \"Avellaneda\"," + "\"gerente\": \"Javier Loeschbor\","
				+ "\"extra_property\": \"Does not fail because mapper is configured to not fail with unknown properties\""
				+ "}";
		 BancoTrucho unBanco;
		  JsonFactory jsonFactory = new JsonFactory();
		 unBanco = jsonFactory.fromJson(jsonStudent, BancoTrucho.class);
		Assert.assertEquals(unBanco.gerente, "Javier Loeschbor");
	}
}