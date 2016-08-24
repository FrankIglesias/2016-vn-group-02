package BancoExterno;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import DesignDreamTeamLocation.Geolocalizacion;
import TypePois.Banco;
import TypePois.POI;

public class ApiDeBancoMock implements ApiDeBancoInterface {

	public ApiDeBancoMock() {
		super();
	}

	private static String rutaDeArchivo = "prueba.json";
	private String jsonBanco = "[{" + "\"banco\":\"Banco de la Plaza\"," + "\"x\":-35.9338322," + "\"y\":72.348353,"
			+ "\"sucursal\":\"Avellaneda\"," + "\"gerente\":\"Javier Loeschbor\"," + "\"servicios\": ["
			+ "\"depositos\",\"extracciones\",\"transferencias\",\"seguros\"" + "]}" + "," + "{"
			+ "\"banco\":\"Banco de la Plaza\"," + "\"x\":-35.9338322," + "\"y\":72.348353,"
			+ "\"sucursal\":\"Caballito\"," + "\"gerente\":\"Fabian Fantaguzzi\"," + "\"servicios\": ["
			+ "\"depositos\", \"extracciones\", \"transferencias\", \"seguros\"" + "]}" + "]";

	public static void setRutaDeArchivo(String ruta) {
		rutaDeArchivo = ruta;
	}

	public static Banco obtenerBancoDesdeArchivo() throws FileNotFoundException {
		Gson gson = new Gson();
		Banco bancoSistema = null;
		Reader read = obtenerReader();
		BancoAdapter banco = gson.fromJson(read, BancoAdapter.class);
		bancoSistema = new Banco(new Geolocalizacion(banco.x, banco.y, null, null), banco.banco, banco.servicios, null);
		return bancoSistema;
	}

	public static Reader obtenerReader() throws FileNotFoundException {
		Reader read = null;
		try {
			read = new FileReader(
					System.getProperty("user.dir") + System.getProperty("file.separator") + rutaDeArchivo);
			System.out.println(read);
		} catch (FileNotFoundException e) {
			System.out.println("FALLO ABRIR EL ARCHIVO CON EL BANCO DE PRUEBA!!!!!!\n");
			throw new FileNotFoundException();
		}
		return read;
	}

	public List<POI> obtenerBancoDesdeString() {
		Gson gson = new Gson();
		java.lang.reflect.Type tipolistaDeBancosTruchos = new TypeToken<List<BancoAdapter>>() {
		}.getType();
		List<BancoAdapter> bancostruchos = gson.fromJson(jsonBanco, tipolistaDeBancosTruchos);
		List<POI> bancosPosta = new ArrayList<POI>();
		bancostruchos.forEach(banco -> bancosPosta.add(new Banco(new Geolocalizacion(banco.x, banco.y, null, null),
				banco.banco, new ArrayList<String>(), null)));
		return bancosPosta;
	}

}