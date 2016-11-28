package MongoDb;

import java.net.UnknownHostException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

import TypePois.POI;

public class MongoManager {
	public static String mappearUnPoi(POI unPoi) throws JsonProcessingException {
		ObjectMapper map = new ObjectMapper();
		return map.writeValueAsString(unPoi);
	}

	public static void guardarPoi(POI unPOI) {
		MongoClient cliente = null;
		try {
			cliente = new MongoClient();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		DB database = cliente.getDB("POIS");
		DBCollection collection = database.getCollection("POIS");
		DBObject doc = new BasicDBObject();
		try {
			doc = (DBObject) JSON.parse(mappearUnPoi(unPOI));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		collection.drop();
		collection.save(doc);
	}
	
}
