package GobiernoDeLaCiudadExterno;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimerTask;

import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import DesignDreamTeamErrors.ErrorHandler;
import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamProcesses.GestorDeProcesos;
import Repositorio.RepoPOIs;
import TypePois.POI;
import TypePois.genericPOI;

public class DarDeBajaPOIDesdeREST extends TimerTask implements ProcessDarDeBajaPOInterface {
	ErrorHandler AccionDeError;
	String url;
	Map<Geolocalizacion, LocalDateTime> POIsAEliminar = new HashMap<Geolocalizacion, LocalDateTime>();
	private Client client;
	private static String API_GOOGLE = "";
	private static String RESOURCE = "";

	public static void setRestParameters(String api, String res) {
		API_GOOGLE = api;
		RESOURCE = res;
	}

	@Override
	public void run() {
		try {
			String noProcesado = null;
			System.out.println("Obteniendo datos...");
			noProcesado = httpGet(url);
			if (noProcesado != null) {
				System.out.println("Procesando datos...");
				POIsAEliminar = procesarPedido(noProcesado);
				System.out.println("Por realizarse...");
				this.eliminarPOIs();
				System.out.println("Realizado Correctamente");
			} else {
				throw new RuntimeException();
			}
		} catch (RuntimeException | IOException e) {
			AccionDeError.ejecutarAccion(new Date(), this);
		}
		
		SemVamoASincronizarno_signal();
	}

	private void SemVamoASincronizarno_signal() {
		GestorDeProcesos.sem.release();
	}
	public Map<Geolocalizacion, LocalDateTime> procesarPedido(String noProcesado) {
		Gson gson = new Gson();
		java.lang.reflect.Type tipo = new TypeToken<List<POIAEliminarADapter>>() {
		}.getType(); // Copiado de ApiDeBancoMock. La fecha es un string
		List<POIAEliminarADapter> POIsPrev = gson.fromJson(noProcesado, tipo);

		Map<Geolocalizacion, LocalDateTime> POIs = new HashMap<Geolocalizacion, LocalDateTime>(); // para
																									// transformar
																									// la
																									// fecha
																									// en
																									// LocalDateTime

		for (POIAEliminarADapter i : POIsPrev) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			Date d = null;
			try {
				d = sdf.parse(i.deletedAt);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			LocalDateTime dateTime = LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault());

			Geolocalizacion geo = new Geolocalizacion(i.latitud, i.longitud, null, null);
			POIs.put(geo, dateTime);
		}

		return POIs;
	}

	public POI getPOI(Geolocalizacion geo) {
		POI p = new genericPOI(geo, "");
		return p;
	}

	public void eliminarPOIs() {
		for (Entry<Geolocalizacion, LocalDateTime> POI : POIsAEliminar.entrySet()) {
			Geolocalizacion clave = POI.getKey();
			LocalDateTime valor = POI.getValue();
			if (valor.toInstant(null).isAfter(LocalDateTime.now().toInstant(null))) {
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

	public String obtenerStream() {
		ClientResponse response = this.obtenerJsonDeUrl("latitud", "");
		return response.getEntity(String.class);
	}

	public ClientResponse obtenerJsonDeUrl(String filter, String value) {
		this.client = Client.create();
		WebResource recurso = this.client.resource(API_GOOGLE).path(RESOURCE);
		WebResource recursoConParametros = recurso.queryParam("banks", filter + ": " + value);
		WebResource.Builder builder = recursoConParametros.accept(MediaType.APPLICATION_JSON);
		ClientResponse response = builder.get(ClientResponse.class);
		return response;
	}

}
