package GobiernoDeLaCiudadExterno;

import java.util.Date;
import java.util.List;

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

public class DarDeBajaPOI extends DesignDreamTeamProcess implements ProcessDarDeBajaPOInterface {

	public DarDeBajaPOI(DDTErrorHandler accion, Date date) {
		super(accion, date);
		// TODO Auto-generated constructor stub
	}

	private Client client;
	private static final String API_GOOGLE = "https://demo4399221.mockable.io/";
	private static final String RESOURCE = "MyExampleRest";
	String jsonDePois = null;
	RepoPOIs repoPoi = RepoPOIs.getInstance();

	public void run() {
		Gson gson = new Gson();
		java.lang.reflect.Type listaDeGeolocalizaciones = new TypeToken<List<Geolocalizacion>>() {
		}.getType();
		List<Geolocalizacion> listita = gson.fromJson(jsonDePois, listaDeGeolocalizaciones);

		listita.stream().forEach(unaGeoAEliminar -> repoPoi.sacarPoiConGeo(unaGeoAEliminar));
		}

	public String obtenerStream() {
		ClientResponse response = this.getBookByFilter("latitud", "");
		return response.getEntity(String.class);
	}
	
	public ClientResponse getBookByFilter(String filter, String value) {
		this.client = Client.create();
		WebResource recurso = this.client.resource(API_GOOGLE).path(RESOURCE);
		WebResource recursoConParametros = recurso.queryParam("banks", filter + ": " + value);
		WebResource.Builder builder = recursoConParametros.accept(MediaType.APPLICATION_JSON);
		ClientResponse response = builder.get(ClientResponse.class);
		return response;
	}

}
