package GobiernoDeLaCiudadExterno;

import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import DesignDreamTeamErrorHandlers.DDTErrorHandler;
import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamProcesses.DesignDreamTeamProcess;
import Repositorios.RepoPOIs;

public class DarDeBajaPOIMock extends DesignDreamTeamProcess implements ProcessDarDeBajaPOInterface {
	
	public DarDeBajaPOIMock(DDTErrorHandler accion, Date date) {
		super(accion, date);
		// TODO Auto-generated constructor stub
	}

	private String restJson = "[{" + "\"latitud\":" + "48.2088611," + "\"longitud\":" + "16.3698108," + "\"deletedAt\":"
			+ "\"2016-06-22T02:10:58.128Z\"" + "}," + "{" + "\"latitud\":" + "16.3990259," + "\"longitud\":"
			+ "48.2146077," + "\"deletedAt\":" + "\"2016-06-22T02:12:58.128Z\"" + "}]";

	RepoPOIs repoPoi = RepoPOIs.getInstance();

	@Override
	public void run() {

		Gson gson = new Gson(); 
		java.lang.reflect.Type listaDeGeolocalizaciones = new TypeToken<List<Geolocalizacion>>() {
		}.getType();
		List<Geolocalizacion> listita = gson.fromJson(restJson, listaDeGeolocalizaciones);

		listita.stream().forEach(unaGeoAEliminar -> repoPoi.sacarPoiConGeo(unaGeoAEliminar));

	}


}


