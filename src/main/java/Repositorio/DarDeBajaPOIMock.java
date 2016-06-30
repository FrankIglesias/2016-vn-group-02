package Repositorio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import DesignDreamTeamLocation.Geolocalizacion;
import TypePois.POI;
import TypePois.genericPOI;
 
public class DarDeBajaPOIMock extends TimerTask implements processDarDeBajaPOI {
	
	String noProcesado = "[{\"latitud\":-35.9338322,\"longitud\":72.348353,\"fecha\":\"1986-04-08 12:30\"},"
			+ " {\"latitud\":-35.9566622,\"longitud\":72.566653,\"fecha\":\"2017-04-08 12:30\"}]";
	Map<Geolocalizacion,LocalDateTime> POIsAEliminar = new HashMap<Geolocalizacion,LocalDateTime>();

	@Override
	public void run() { //realiza el proceso. lo copié de otro proceso
		System.out.println("Obteniendo datos...");
		System.out.println("Procesando datos...");
		
		System.out.println(noProcesado);
		
		POIsAEliminar = procesarPedido(noProcesado);
		System.out.println("Por realizarse...");
		this.eliminarPOIs();
		System.out.println("Realizado Correctamente");
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
		POI p = new genericPOI(geo,""); //crea un POI generico para comparar (no tengo los datos para saber que tipo de poi es, ni me interesa tenerlos)
		return p;
	}

	public void eliminarPOIs() {
		for (Entry<Geolocalizacion,LocalDateTime> POI : POIsAEliminar.entrySet()) {
			Geolocalizacion clave = POI.getKey();
			LocalDateTime valor = POI.getValue();
			if(valor.toInstant(null).isAfter(LocalDateTime.now().toInstant(null))) {
				RepoPOIs.getInstance().sacarPoi(this.getPOI(clave)); //borra el poi si la fecha es mayor a la actual
			}
		}
	}
}
