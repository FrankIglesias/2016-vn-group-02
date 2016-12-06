package tests;

import java.net.UnknownHostException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import BancoExterno.ApiDeBancoReal;
import BancoExterno.BancoAdapter;
import BancoExterno.JsonFactory;

public class TestMongoBancos {

	@Test
	public void testPersistenciaDesdeExterno() throws UnknownHostException, JsonProcessingException {

		ApiDeBancoReal requester = new ApiDeBancoReal();
		String json = requester.obtenerStream();

		String[] bancos = json.split("}");

		BancoAdapter unBanco;
		JsonFactory jsonFactory = new JsonFactory();

		unBanco = jsonFactory.fromJson(bancos[0].substring(1).concat("}"), BancoAdapter.class);
	}

}
