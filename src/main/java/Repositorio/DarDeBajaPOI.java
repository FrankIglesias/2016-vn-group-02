package Repositorio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimerTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import DesignDreamTeamLocation.Geolocalizacion;
import TypePois.POI;
import TypePois.genericPOI;

public class DarDeBajaPOI extends TimerTask implements processDarDeBajaPOI {
	
	String url;
	Map<Geolocalizacion,LocalDateTime> POIsAEliminar = new HashMap<Geolocalizacion,LocalDateTime>();
 
	@Override
	public void run() {
		String noProcesado = null;
		System.out.println("Obteniendo datos...");
		try {
			noProcesado = httpGet(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(noProcesado != null)
		{
			System.out.println("Procesando datos...");
			POIsAEliminar = procesarPedido(noProcesado);
			System.out.println("Por realizarse...");
			this.eliminarPOIs();
			System.out.println("Realizado Correctamente");
		}
		else
		{
			System.out.println("Error!");
		}
		
	}

	public Map<Geolocalizacion, LocalDateTime> procesarPedido(String noProcesado) { //Procesa el string json para transformarlo en un Map
		Gson gson = new Gson();
		java.lang.reflect.Type tipo = new TypeToken<List<restPOIAEliminar>>() {}.getType(); //Copiado de ApiDeBancoMock. La fecha es un string
		List<restPOIAEliminar> POIsPrev = gson.fromJson(noProcesado, tipo);
		
		Map<Geolocalizacion,LocalDateTime> POIs = new HashMap<Geolocalizacion,LocalDateTime>(); //para transformar la fecha en LocalDateTime
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); //formato para la fecha
		
		for(restPOIAEliminar i : POIsPrev)
		{
			LocalDateTime dateTime = LocalDateTime.parse(i.fecha, formatter);
			Geolocalizacion geo = new Geolocalizacion(i.latitud, i.longitud, null, null);
			POIs.put(geo, dateTime);
		}
		
		return POIs;
	}
	
	public POI getPOI(Geolocalizacion geo) {
		POI p = new genericPOI(geo,"");
		return p;
	}

	public void eliminarPOIs() {
		for (Entry<Geolocalizacion,LocalDateTime> POI : POIsAEliminar.entrySet()) {
			Geolocalizacion clave = POI.getKey();
			LocalDateTime valor = POI.getValue();
			if(valor.toInstant(null).isAfter(LocalDateTime.now().toInstant(null))) {
				RepoPOIs.getInstance().sacarPoi(this.getPOI(clave));
			}
		}
	}

	public static String httpGet(String urlStr) throws IOException {
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		if (conn.getResponseCode() != 200) {
			throw new IOException(conn.getResponseMessage());
		}

		// Buffer the result into a string
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();

		conn.disconnect();
		return sb.toString();
	}

}
