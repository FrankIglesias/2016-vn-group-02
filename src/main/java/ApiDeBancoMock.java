import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class ApiDeBancoMock {
	private static String stream =

			"{\"banco\":\"Banco de la Plaza\"," + "\"x\":-35.9345681," + "\"y\":72.344546,"
					+ "\"sucursal\":\"Caballito\"," + "\"gerente\":\"Fabian Fantaguzzi\"" + "}";

	public static Banco parse() {
		Gson gson = new Gson();
		BancoTrucho banco = gson.fromJson(stream, BancoTrucho.class);
		Banco bancoSistema = new Banco(new Geolocalizacion(banco.x, banco.y, null, null), banco.banco,
				new ArrayList<String>(), null);
		return bancoSistema;
	}
}