import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientResponse;

public class ApiDeBancoMock implements ApiDeBanco {
	private static String ruta = "/home/frank/";

	public static Banco parse() {
		Gson gson = new Gson();
		Banco bancoSistema = null; 
		BancoTrucho banco = gson.fromJson(reader, BancoTrucho.class);
		bancoSistema = new Banco(new Geolocalizacion(banco.x, banco.y, null, null), banco.banco,
				new ArrayList<String>(), null);
		return bancoSistema;
	}

	@Override
	public String obtenerBancos() {
		try (Reader read = new FileReader(ruta + "2016-vn-group-02/src/main/java/prueba.json")) {
			IOUtils.toString(read);
			 
			
		
		} catch (IOException e) {
			//return "violacion de segmento";
		}
		
		return "";
		
		}
		

	@Override
	public ClientResponse getBookAndSendHeader(String filter, String value, String header, String headerValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientResponse getBookByFilter(String filter, String value, String fields) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientResponse getBookByFilter(String filter, String value) {
		// TODO Auto-generated method stub
		return null;
	}
}