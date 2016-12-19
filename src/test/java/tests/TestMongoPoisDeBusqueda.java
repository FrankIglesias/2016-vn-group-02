package tests;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import Repositorios.Buscador;
import Repositorios.PoisBusquedaParser;
import Repositorios.Terminal;
import TypePois.Banco;
import TypePois.POI;

public class TestMongoPoisDeBusqueda {

	public Buscador buscador;
	public Banco banco;
	String fraseABuscar;
	Terminal terminal = new Terminal("Terminal 1");
	List<POI> listaDePOIs;
	
	@Before
	public void init() {

		banco = GlobalTestVariables.crearUnBanco(GlobalTestVariables.crearFeriadoVacio());
		fraseABuscar = "Quiero un Banco Rio";
		listaDePOIs = new ArrayList<POI>();
		listaDePOIs.add(banco);
		buscador = new Buscador();
		Buscador.setPuntosDeIntereses(listaDePOIs);
	}
	
	@Test
	public void encuentraMasDeUnPOIExternoYNoExternoSegunPalabraClave() throws UnknownHostException
	{
		PoisBusquedaParser.guardarPoisDeUnaBusqueda(buscador.buscarPoisMongo(fraseABuscar, terminal));
		
		MongoClient cliente = new MongoClient();
		DB database = cliente.getDB("PoisDeBusquedas");
		DBCollection collection = database.getCollection("PoisDeBusquedas");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("id", 0);
		DBCursor cursor = collection.find(whereQuery);

		Gson gson = new Gson();
		Banco unPoi = gson.fromJson(cursor.next().toString(), Banco.class);
		Assert.assertEquals(unPoi.getId(), 0);
	}
	
}
