package MongoDb;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import DesignDreamTeamLocation.Geolocalizacion;
import TypePois.Banco;

public class ParserBanco {
	public static void guardarBanco(Banco unBanco) {
		MongoClient cliente = null;
		try {
			cliente = new MongoClient();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB database = cliente.getDB("Banco");
		DBCollection collection = database.getCollection("Banco");
		BasicDBObject doc = new BasicDBObject();
		doc.put("id", unBanco.getId());
		doc.put("nombre", unBanco.getNombre());
		doc.put("palabras clave", unBanco.getPalabrasClave());
		doc.put("Geolocalizacion", parsearUnPoint(unBanco.getPoint()));
		doc.put("Feriados", null);
		collection.drop();
		collection.save(doc);
	}

	private static BasicDBObject parsearUnPoint(Geolocalizacion point) {
		BasicDBObject objeto = new BasicDBObject();
		objeto.put("Latitud", point.getLatitud());
		objeto.put("Domicilio", "");
		objeto.put("Longitud", point.getLongitud());
		objeto.put("Localidad", "");
		return objeto;
	}
}
