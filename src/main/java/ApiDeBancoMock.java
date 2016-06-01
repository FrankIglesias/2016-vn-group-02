import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientResponse;

public class ApiDeBancoMock implements ApiDeBanco {
	private static String ruta = "C:/Users/Juani/git";
	private String jsonBanco = "{" + "\"banco\": \"Banco de la Plaza\"," + "\"x\": -35.9338322," + "\"y\": 72.348353,"
			+ "\"sucursal\": \"Avellaneda\"," + "\"gerente\": \"Javier Loeschbor\","
			+ "\"extra_property\": \"Does not fail because mapper is configured to not fail with unknown properties\""
			+ "}";

	public static Banco obtenerBancoDesdeArchivo() {
		Gson gson = new Gson();
		Banco bancoSistema = null;
		Reader read = obtenerReader();
		BancoTrucho banco = gson.fromJson(read, BancoTrucho.class);
		bancoSistema = new Banco(new Geolocalizacion(banco.x, banco.y, null, null), banco.banco,
				new ArrayList<String>(), null);
		return bancoSistema;
	}

	public static Reader obtenerReader() {
		Reader read = null;
		try {
			read = new FileReader(ruta + "/2016-vn-group-02/src/main/java/prueba.json");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return read;
	}

	public ClientResponse getBookAndSendHeader(String filter, String value, String header, String headerValue) {
		// TODO Auto-generated method stub
		return null;
	}

	public ClientResponse getBookByFilter(String filter, String value, String fields) {
		// TODO Auto-generated method stub
		return null;
	}

	public ClientResponse getBookByFilter(String filter, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	public Banco obtenerBancos() {
		JsonFactory jsonFactory = new JsonFactory();
		BancoTrucho banco = jsonFactory.fromJson(jsonBanco, BancoTrucho.class);
		Banco bancoSistema = new Banco(new Geolocalizacion(banco.x, banco.y, null, null), banco.banco,
				new ArrayList<String>(), null);
		return bancoSistema;
	}

	@Override
	public String obtenerStream() {
		// TODO Auto-generated method stub
		return null;
	}
}