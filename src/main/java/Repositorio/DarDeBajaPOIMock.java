package Repositorio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import DesignDreamTeamLocation.Geolocalizacion;
import TypePois.POI;
import TypePois.genericPOI;
 
public class DarDeBajaPOIMock extends TimerTask implements processDarDeBajaPOI {

	String noProcesado = "[{[\"latitud\":-35.9338322,\"longitud\":72.348353,\"domicilio\":null,\"localidad\":null],1986-04-08 12:30},"
						+ "{[\"latitud\":-35.9366622,\"longitud\":72.366653,\"domicilio\":null,\"localidad\":null],2016-04-08 12:30}]";
	Map<Geolocalizacion,LocalDateTime> POIsAEliminar = new HashMap<Geolocalizacion,LocalDateTime>();

	@Override
	public void run() {
		System.out.println("Obteniendo datos...");
		System.out.println("Procesando datos...");
		POIsAEliminar = procesarPedido(noProcesado);
		System.out.println("Por realizarse...");
		this.eliminarPOIs();
		System.out.println("Realizado Correctamente");
	}

	public Map<Geolocalizacion, LocalDateTime> procesarPedido(String noProcesado) {
		Gson gson = new Gson();
		java.lang.reflect.Type tipo = new TypeToken<Map<Geolocalizacion,String>>() {
		}.getType();
		Map<Geolocalizacion,String> POIsPrev = gson.fromJson(noProcesado, tipo);
		
		Map<Geolocalizacion,LocalDateTime> POIs = new HashMap<Geolocalizacion,LocalDateTime>();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
		for (Entry<Geolocalizacion,String> POI : POIsPrev.entrySet()) {
			LocalDateTime dateTime = LocalDateTime.parse(POI.getValue(), formatter);
			POIs.put(POI.getKey(),dateTime);
		}
		
		return POIs;
	}
	
	public POI getPOI(Geolocalizacion geo) {
		POI p = new genericPOI(geo);
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
}
