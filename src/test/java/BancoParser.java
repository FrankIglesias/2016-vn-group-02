import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class BancoParser {

	private JsonFactory jsonFactory = new JsonFactory();
	BancoTrucho unBanco;
	private String jsonStudent = "{" + "\"banco\": \"Banco de la Plaza\"," + "\"x\": -35.9338322," + "\"y\": 72.348353,"
			+ "\"sucursal\": \"Avellaneda\"," + "\"gerente\": \"Javier Loeschbor\","
			+ "\"extra_property\": \"Does not fail because mapper is configured to not fail with unknown properties\""
			+ "}";
//NO SE PORQUE EL EXTRA PROPERTY SI SE LO SACO ME FALLA
	@Before
	public void setUp() {
		unBanco = jsonFactory.fromJson(jsonStudent, BancoTrucho.class);
	}

	@Test
	public void testParsearYObtenerUnBanco() {
		Assert.assertEquals(unBanco.gerente, "Javier Loeschbor");
	}

}



