import java.io.FileReader;
import java.io.Reader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

public class ApiDeBancoMock {
	private static String stream =

			"{\"banco\":\"Banco de la Plaza\"," + "\"x\":-35.9345681," + "\"y\":72.344546,"
					+ "\"sucursal\":\"Caballito\"," + "\"gerente\":\"Fabian Fantaguzzi\"" + "}";

	public static Banco parse() {
		Gson gson = new Gson();
		Banco bancoSistema = null; 
try(Reader reader = new FileReader("/home/frank/2016-vn-group-02/src/main/java/prueba.json")){
		BancoTrucho banco = gson.fromJson(reader, BancoTrucho.class);
		bancoSistema = new Banco(new Geolocalizacion(banco.x, banco.y, null, null), banco.banco,
				new ArrayList<String>(), null);
}catch(IOException e){
	System.out.println("violacion de segmento");
}
		return bancoSistema;
	}
}