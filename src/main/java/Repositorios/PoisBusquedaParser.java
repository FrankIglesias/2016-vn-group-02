package Repositorios;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import TypePois.POI;

public class PoisBusquedaParser {
	
	
	public static void guardarPoisDeUnaBusqueda(List<POI> pois) {
		
		MongoClient cliente = null;
		try {
			cliente = new MongoClient();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		int i = 0;
		
		for (i=0; i < pois.size(); i++) { 
		DB database = cliente.getDB("PoisDeBusquedas");
		DBCollection collection = database.getCollection("PoisDeBusquedas");
		BasicDBObject doc = new BasicDBObject();
		POI unPoi = pois.get(i);
		doc.put("numeroDeBusqueda", 0);
		doc.put("id", unPoi.getId());
		doc.put("nombre", unPoi.getNombre());
		doc.put("palabrasClave", unPoi.getPalabrasClave());
		collection.drop();
		collection.save(doc);
		}
		
	}
}
