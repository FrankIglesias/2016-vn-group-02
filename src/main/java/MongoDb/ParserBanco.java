package MongoDb;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import BancoExterno.BancoAdapter;



public class ParserBanco {
	public static void guardarBanco (BancoAdapter unBanco) {
		MongoClient cliente = null;
			try {
				cliente = new MongoClient();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		DB database = cliente.getDB("Banco");
		DBCollection collection = database.getCollection("Banco");
		BasicDBObject doc = new BasicDBObject();
		doc.put("id", unBanco.getId());
		doc.put("nombre", unBanco.getBanco());
		doc.put("palabras clave", unBanco.getServicios());
		doc.put("Geolocalizacion", parsearUnPoint(unBanco));
		collection.drop();
		collection.save(doc);
	}

	private static BasicDBObject parsearUnPoint(BancoAdapter unBanco) {
		BasicDBObject objeto = new BasicDBObject();
		objeto.put("Latitud", unBanco.getY());
		objeto.put("Longitud", unBanco.getX());
		return objeto;
	}
	
}
