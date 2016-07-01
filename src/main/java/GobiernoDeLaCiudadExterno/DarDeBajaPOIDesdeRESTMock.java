package GobiernoDeLaCiudadExterno;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import javax.ws.rs.core.MediaType;

import java.util.Map.Entry;

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
 
public class DarDeBajaPOIDesdeRESTMock extends TimerTask implements ProcessDarDeBajaPOInterface {
	ErrorHandler AccionDeError;
	String noProcesado;
	Map<Geolocalizacion,LocalDateTime> POIsAEliminar = new HashMap<Geolocalizacion,LocalDateTime>();
	private Client client;
	private static final String API_GOOGLE = "https://demo4399221.mockable.io/";
	private static final String RESOURCE = "MyExampleRest";

	@Override
	public void run() {
		try {
		System.out.println("Obteniendo datos...");
		noProcesado = this.obtenerStream();
		System.out.println("Procesando datos...");
		POIsAEliminar = procesarPedido(noProcesado);
		System.out.println("Por realizarse...");
		this.eliminarPOIs();
		System.out.println("Realizado Correctamente");
		}
		catch(RuntimeException e){
			AccionDeError.ejecutarAccion(new Date(),this);
			
		}
		
		SemVamoASincronizarno_signal();
	}

	private void SemVamoASincronizarno_signal() {
		GestorDeProcesos.sem.release();
	}
	
	public Map<Geolocalizacion, LocalDateTime> procesarPedido(String noProcesado) { //Procesa el string json para transformarlo en un Map
		Gson gson = new Gson();
		java.lang.reflect.Type tipo = new TypeToken<List<POIAEliminarADapter>>() {}.getType(); //Copiado de ApiDeBancoMock. La fecha es un string
		List<POIAEliminarADapter> POIsPrev = gson.fromJson(noProcesado, tipo);
		
		Map<Geolocalizacion,LocalDateTime> POIs = new HashMap<Geolocalizacion,LocalDateTime>(); //para transformar la fecha en LocalDateTime
		
		for(POIAEliminarADapter i : POIsPrev)
		{
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
		POI p = new genericPOI(geo,""); //crea un POI generico para comparar (no tengo los datos para saber que tipo de poi es, ni me interesa tenerlos)
		return p;
	}

	public void eliminarPOIs() {
		for (Entry<Geolocalizacion,LocalDateTime> POI : POIsAEliminar.entrySet()) {
			Geolocalizacion clave = POI.getKey();
			LocalDateTime valor = POI.getValue();
			if(valor.isBefore(LocalDateTime.now())) {
				RepoPOIs.getInstance().sacarPoi(this.getPOI(clave)); //borra el poi si la fecha es mayor a la actual
			}
		}
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
