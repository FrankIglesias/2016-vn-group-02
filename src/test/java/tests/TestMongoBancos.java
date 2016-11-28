package tests;

import java.net.UnknownHostException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

import BancoExterno.ApiDeBancoReal;
import BancoExterno.BancoAdapter;
import BancoExterno.JsonFactory;
import MongoDb.MongoManager;

public class TestMongoBancos {

	@Test
	public void testPersistenciaDesdeExterno() throws UnknownHostException, JsonProcessingException {

		ApiDeBancoReal requester = new ApiDeBancoReal();
		String json = requester.obtenerStream();

		String[] bancos = json.split("}");

		BancoAdapter unBanco;
		JsonFactory jsonFactory = new JsonFactory();

		unBanco = jsonFactory.fromJson(bancos[0].substring(1).concat("}"), BancoAdapter.class);
		MongoManager.guardarPoi(GlobalTestVariables.crearUnColectivo());
//		MongoClient cliente = new MongoClient();
//		DB database = cliente.getDB("Banco");
//		DBCollection collection = database.getCollection("Banco");
//		BasicDBObject whereQuery = new BasicDBObject();
//		whereQuery.put("nombre", "Banco de la Plaza");
//		DBCursor cursor = collection.find(whereQuery);
//		Gson gson = new Gson();
//		Banco nuevoBanco = gson.fromJson(cursor.next().toString(), Banco.class);
//		Assert.assertEquals(nuevoBanco.getId(), 0);
	}

}
