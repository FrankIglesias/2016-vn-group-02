package tests;

import java.net.UnknownHostException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

import BancoExterno.ApiDeBancoReal;
import BancoExterno.BancoAdapter;
import BancoExterno.JsonFactory;
import MongoDb.ParserBanco;
import TypePois.Banco;


public class TestMongoBancos {
	
	@Test
	public void testPersistenciaDesdeExterno() throws UnknownHostException {
		
		ApiDeBancoReal requester = new ApiDeBancoReal();
		String json = requester.obtenerStream();
		
		String[] bancos = json.split("}");
		
		BancoAdapter unBanco;
		JsonFactory jsonFactory = new JsonFactory();
		

		unBanco = jsonFactory.fromJson(bancos[0].substring(1).concat("}"), BancoAdapter.class);
		ParserBanco.guardarBanco(unBanco);
		DBObject dbObject = (DBObject) JSON.parse("{'name':'mkyong', 'age':30}");
		MongoClient cliente = new MongoClient();
		DB database = cliente.getDB("Banco");
		DBCollection collection = database.getCollection("Banco");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("nombre", "Banco de la Plaza");
		DBCursor cursor = collection.find(whereQuery);
		Gson gson = new Gson();
		Banco nuevoBanco = gson.fromJson(cursor.next().toString(), Banco.class);
		Assert.assertEquals(nuevoBanco.getId(), 0);
	}

}
