package tests;

import java.net.UnknownHostException;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import MongoDb.ParserBanco;
import TypePois.Banco;


public class TestMongo {

	@Test
	public void test() throws UnknownHostException {
		ParserBanco.guardarBanco(GlobalTestVariables.crearUnBanco(null));
		MongoClient cliente = new MongoClient();
		DB database = cliente.getDB("Banco");
		DBCollection collection = database.getCollection("Banco");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("nombre", "Banco Rio");
		DBCursor cursor = collection.find(whereQuery);
		Gson gson = new Gson();
		Banco nuevoBanco = gson.fromJson(cursor.next().toString(), Banco.class);
		Assert.assertEquals(nuevoBanco.getId(), 0);
	}

}
