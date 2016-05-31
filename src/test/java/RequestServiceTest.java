import com.sun.jersey.api.client.ClientResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by niko118 on 5/1/16.
 */
public class RequestServiceTest {

	RequestService requester;

	@Before
	public void setUp() throws Exception {
		this.requester = new RequestService();
	}

	@Test
	public void testDeObtenerBancosDesdeLaAPI() throws Exception {
		String json = requester.obtenerBancos();
		assertTrue(json.contains("sucursal"));

	}

}
