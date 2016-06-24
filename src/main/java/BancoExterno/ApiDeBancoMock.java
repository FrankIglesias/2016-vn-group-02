package BancoExterno;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import DesignDreamTeamLocation.Geolocalizacion;
import TypePois.Banco;

public class ApiDeBancoMock implements ApiDeBancoInterface {
	private static String rutaDeArchivo;
	private String jsonBanco = "[{" + "\"banco\":\"Banco de la Plaza\"," + "\"x\":-35.9338322," + "\"y\":72.348353,"
			+ "\"sucursal\":\"Avellaneda\"," + "\"gerente\":\"Javier Loeschbor\"," + "\"servicios\": ["
			+ "\"depositos\",\"extracciones\",\"transferencias\",\"seguros\"" + "]}" + "," + "{"
			+ "\"banco\":\"Banco de la Plaza\"," + "\"x\":-35.9338322," + "\"y\":72.348353,"
			+ "\"sucursal\":\"Caballito\"," + "\"gerente\":\"Fabian Fantaguzzi\"," + "\"servicios\": ["
			+ "\"depositos\", \"extracciones\", \"transferencias\", \"seguros\"" + "]}" + "]";

	public static void setRuta() {
	
		rutaDeArchivo =  System.getProperty("user.dir");
		String nombreArchivo =  "prueba.json";
		System.out.println(rutaDeArchivo + "\\"+ nombreArchivo);
	}

	public static Banco obtenerBancoDesdeArchivo() {
		Gson gson = new Gson();
		Banco bancoSistema = null;
		Reader read = obtenerReader();
		BancoTrucho banco = gson.fromJson(read, BancoTrucho.class);
		bancoSistema = new Banco(new Geolocalizacion(banco.x, banco.y, null, null), banco.banco, banco.servicios, null);
		return bancoSistema;
	}

	public static Reader obtenerReader() {
		Reader read = null;
		try {
			read = new FileReader(rutaDeArchivo+ "\\" + "prueba.json");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return read;
	}

	public List<Banco> obtenerBancos() {

		Gson gson = new Gson();
		java.lang.reflect.Type tipolistaDeBancosTruchos = new TypeToken<List<BancoTrucho>>() {
		}.getType();
		List<BancoTrucho> bancostruchos = gson.fromJson(jsonBanco, tipolistaDeBancosTruchos);
		List<Banco> bancosPosta = new ArrayList<Banco>();
		bancostruchos.forEach(banco -> bancosPosta.add(new Banco(new Geolocalizacion(banco.x, banco.y, null, null),
				banco.banco, new ArrayList<String>(), null)));
		return bancosPosta;
	}

}