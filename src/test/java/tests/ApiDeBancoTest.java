package tests;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import BancoExterno.ApiDeBancoMock;
import BancoExterno.ApiDeBancoReal;
import BancoExterno.BancoAdapter;
import BancoExterno.JsonFactory;
import TypePois.Banco;
import TypePois.POI;
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
		List<POI> listaDeBancos  = requester.obtenerBancoDesdeString();
		Assert.assertTrue(listaDeBancos.size()==2);

	}

	@Test
	public void testDeObtenerBancosDesdeString() throws Exception {
		List<POI> listaDeBancos = secondRequester.obtenerBancoDesdeString();
		Assert.assertTrue(listaDeBancos.size()==2);

	}
	@Test
	public void testDeObtenerUnBancoObjetoDeMockDeArchivo() throws Exception {
		Banco unBanco = ApiDeBancoMock.obtenerBancoDesdeArchivo();
		Assert.assertEquals(unBanco.getNombre(), "Banco de la Plaza");

	}
	@Test
	public void testDeObtenerUnBancoObjetoDeMockDeArchivoContieneUnServicio() throws Exception {
		Banco unBanco = ApiDeBancoMock.obtenerBancoDesdeArchivo();
		Assert.assertTrue(unBanco.getPalabrasClave().contains("cobro cheques"));
	}

	@Test
	public void testParsearYObtenerUnBanco() {
		 String jsonStudent = "{" + "\"banco\": \"Banco de la Plaza\"," + "\"x\": -35.9338322," + "\"y\": 72.348353,"
				+ "\"sucursal\": \"Avellaneda\"," + "\"gerente\": \"Javier Loeschbor\","
				+ "\"extra_property\": \"Does not fail because mapper is configured to not fail with unknown properties\""
				+ "}";
		 BancoAdapter unBanco;
		  JsonFactory jsonFactory = new JsonFactory();
		 unBanco = jsonFactory.fromJson(jsonStudent, BancoAdapter.class);
		Assert.assertEquals(unBanco.gerente, "Javier Loeschbor");
	}
}