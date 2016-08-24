package GobiernoDeLaCiudadExterno;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import DesignDreamTeamErrorHandlers.DDTErrorHandler;
import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamProcesses.DesignDreamTeamProcess;
import Repositorios.RepoPOIs;
import TypePois.POI;

public class DarDeBajaPOIMock extends DesignDreamTeamProcess implements ProcessDarDeBajaPOInterface {
	DDTErrorHandler AccionDeError;
	String noProcesado;
	Map<Geolocalizacion, LocalDateTime> POIsAEliminar = new HashMap<Geolocalizacion, LocalDateTime>();
	private Client client;
	private static final String API_GOOGLE = "https://demo4399221.mockable.io/";
	private static final String RESOURCE = "MyExampleRest";

	public DarDeBajaPOIMock(DDTErrorHandler accion, Date date) {
		super(accion, date);
	}

	public void setAccionDeError(DDTErrorHandler accionDeError) {
		AccionDeError = accionDeError;
	}

	public void run() {
		try {
			noProcesado = this.obtenerStream();
			POIsAEliminar = procesarPedido(noProcesado);
			this.eliminarPOIs();
		} catch (RuntimeException e) {
			AccionDeError.ejecutarAccion(new Date(), this);
		}
	}

	public Map<Geolocalizacion, LocalDateTime> procesarPedido(String noProcesado) {
		Gson gson = new Gson();
		java.lang.reflect.Type tipo = new TypeToken<List<POIAEliminarADapter>>() {
		}.getType();
		List<POIAEliminarADapter> POIsPrev = gson.fromJson(noProcesado, tipo);
		Map<Geolocalizacion, LocalDateTime> POIs = new HashMap<Geolocalizacion, LocalDateTime>();
		for (POIAEliminarADapter i : POIsPrev) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			Date d = null;
			try {
				d = sdf.parse(i.deletedAt);
			} catch (ParseException e) {
				throw new RuntimeException();
			}

			LocalDateTime dateTime = LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault());

			Geolocalizacion geo = new Geolocalizacion(i.latitud, i.longitud, null, null);
			POIs.put(geo, dateTime);
		}

		return POIs;
	}

	public POI getPOI(Geolocalizacion geo) {
		POI p = new POIGCBAdapter(geo, "");
		return p;
	}

	public void eliminarPOIs() {
		for (Entry<Geolocalizacion, LocalDateTime> POI : POIsAEliminar.entrySet()) {
			Geolocalizacion clave = POI.getKey();
			LocalDateTime valor = POI.getValue();
			if (valor.isBefore(LocalDateTime.now())) {
				RepoPOIs.getInstance().sacarPoi(this.getPOI(clave));
			}
		}
	}

	public String obtenerStream() {
		ClientResponse response = this.obtenerPOIDesdeRESTGCBA("latitud", "");
		return response.getEntity(String.class);
	}

	public ClientResponse obtenerPOIDesdeRESTGCBA(String filter, String value) {
		this.client = Client.create();
		WebResource recurso = this.client.resource(API_GOOGLE).path(RESOURCE);
		WebResource recursoConParametros = recurso.queryParam("banks", filter + ": " + value);
		WebResource.Builder builder = recursoConParametros.accept(MediaType.APPLICATION_JSON);
		ClientResponse response = builder.get(ClientResponse.class);
		return response;
	}
}
