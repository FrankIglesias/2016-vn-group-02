package Repositorios;

import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.swing.text.Document;

import org.bson.types.ObjectId;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

import DesignDreamTeamLocation.Geolocalizacion;
import TypePois.Banco;
import TypePois.CGP;
import TypePois.Local;
import TypePois.POI;

public class RepoPOIs implements WithGlobalEntityManager {

	List<POI> puntosDeIntereses;
	static RepoPOIs instancia;
	EntityManager entityManager = PerThreadEntityManagers.getEntityManager();

	public void persistirEnHibernate(POI unPOI) {
		EntityTransaction transaccion = entityManager.getTransaction();
		transaccion.rollback();
		transaccion.begin();
		entityManager.persist(unPOI);
		transaccion.commit();
	}

	public POI obtenerDeHibernate(int id) {
		return entityManager.find(POI.class, id);
	}

	public List<POI> obtenerDeHibernateSegunPalabrasClave(String palabraClave) {
		return entityManager().createQuery("from POI p join p.palabrasClave pc  WHERE pc = :palabraClave", POI.class)
				.setParameter("palabraClave", palabraClave).getResultList();
	}
	
	public String mappearUnPoi(POI unPoi) throws JsonProcessingException {
		ObjectMapper map = new ObjectMapper();
		return map.writeValueAsString(unPoi);
	}
	
	public DBCollection conexionAMongo() {
		MongoClient cliente = null;
		try {
			cliente = new MongoClient();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		DB database = cliente.getDB("POIS");
		DBCollection collection = database.getCollection("POIS");;
		return collection; 
	}
	

	public void persistirEnMongo(POI unPOI) {

		DBCollection collection = conexionAMongo();
		DBObject doc = new BasicDBObject();
		try {
			doc = (DBObject) JSON.parse(mappearUnPoi(unPOI));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		collection.save(doc);
	}

	public List<POI> obtenerDeMongoSegunPalabrasClave(String palabra) {
		List<POI> pois = new ArrayList<POI>();
		DBCollection collection = conexionAMongo();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("palabrasClave", palabra);
		DBCursor cursor = collection.find(whereQuery);
		while (cursor.hasNext()) {
			System.out.println(cursor.next() + "\n");
		}
		return pois;

	}

	public void levantarTodoDeMongo() {
		DBCollection collection = conexionAMongo();
		DBCursor cursor = collection.find();
		Gson gson = new Gson();
		while (cursor.hasNext()) {
			java.lang.reflect.Type tipoPOI = new TypeToken<POI>() {
			}.getType();
			String id = cursor.next().get("_id").toString();
			POI poiAGuardar = gson.fromJson(cursor.next().toString(), tipoPOI);
			poiAGuardar.setIdMongo(id);
			puntosDeIntereses.add(poiAGuardar);
		}
	}

	public void sincronizarBDs() {
		levantarTodoDeMongo();
		List<POI> aHibernate = puntosDeIntereses.stream().filter(unPoi -> noSeConsultoEn7Dias(unPoi))
				.collect(Collectors.toList());
		aHibernate.forEach(unPoi -> persistirEnHibernate(unPoi));
		aHibernate.stream().forEach(unPoint -> borrarDeMongo(unPoint));
	}

	public void borrarDeMongo(POI unPoi) {
		conexionAMongo().remove(new BasicDBObject("_id", new ObjectId(unPoi.idMongo)));;
	}

	public void borrarDeHibernate(POI unPoi) {
		entityManager.remove(unPoi);
	}
	public boolean noSeConsultoEn7Dias(POI unPoi) {
		return !(LocalDateTime.now().minusWeeks(1).isBefore(unPoi.getFechaDeBusqueda()));
	}

	public static RepoPOIs getInstance() {
		if (instancia == null) {
			instancia = new RepoPOIs();
			instancia.inicializarPuntosDeIntereses();
		}
		return instancia;
	}

	public void inicializarPuntosDeIntereses() {
		puntosDeIntereses = new ArrayList<POI>();
	}

	private boolean sonIguales(POI point1, POI point2) {
		return point1.getPoint().getLatitud() == point2.getPoint().getLatitud()
				&& point1.getPoint().getLongitud() == point2.getPoint().getLongitud();
		// dos point son iguales si estan exactamente en el mismo punto.
	}

	public void agregarVariosPoiDeListaDeBancos(List<Banco> listaDeBanco) {
		puntosDeIntereses.addAll(listaDeBanco);
	}

	public void agregarVariosCGPDeListaDeCGP(List<CGP> listaDeCGP) {
		puntosDeIntereses.addAll(listaDeCGP);
	}

	public List<POI> tieneUnLocalConNombre(String nombre) {

		return puntosDeIntereses.stream().filter(unLocal -> unLocal.getNombre().equals(nombre))
				.collect(Collectors.toList());
	}

	public void modificarUnPoi(POI unPoi, String nombre, Geolocalizacion unaGeo) {
		
		 BasicDBObject aModificar = new BasicDBObject().append("_id", unPoi.getIdMongo());
		 BasicDBObject modificado = new BasicDBObject("$set", 
		    		new BasicDBObject().
		    		append("nombre", nombre).
		    		append("point", new BasicDBObject().
		    				append("latitud", unaGeo.getLatitud()).
		    				append("longitud", unaGeo.getLongitud()).
		    				append("domicilio", new BasicDBObject().
		    						append("callePrincipal", unaGeo.getDomicilio().getCallePrincipal()).
		    						append("altura", unaGeo.getDomicilio().getAltura()))));
		 
		 POI poiAModificar = obtenerDeHibernate(unPoi.getId());
		 poiAModificar.setNombre(nombre);
		 poiAModificar.setGeo(unaGeo);
		 
		if(!unPoi.idMongo.isEmpty())
		{
			conexionAMongo().update(aModificar, modificado);
			entityManager.merge(poiAModificar);
		}
		else
		{
			entityManager.merge(poiAModificar);
		}
		
	}
	
	public void actualizarLocal(String nombre, ArrayList<String> palabrasClave) {

		POI localAModificar = this.tieneUnLocalConNombre(nombre).get(0);
		puntosDeIntereses.remove(localAModificar);

		localAModificar.setPalabrasClave(palabrasClave);
		puntosDeIntereses.add(localAModificar);

	}
	
	public void addLocal(String nombre, ArrayList<String> palabrasClave) {
		Local localito = new Local(null, nombre, null, null, null);

		puntosDeIntereses.remove(localito);
		localito.setPalabrasClave(palabrasClave);
		puntosDeIntereses.add(localito);
	}

	public boolean tieneLasPalabrasClaves(String poi, ArrayList<ArrayList<String>> palabrasClaves) {
		return puntosDeIntereses.stream().anyMatch(unPOI -> (unPOI.getNombre().equals(poi))
				&& palabrasClaves.stream().anyMatch((unaLista -> unPOI.tenesTodasLasPalabrasClaves(unaLista))));
	}

	public void sacarPoiConGeo(Geolocalizacion geo) {
		puntosDeIntereses.remove(puntosDeIntereses.stream().filter(unPoi -> mismaGeolocalizacion(geo, unPoi))
				.collect(Collectors.toList()).get(0));
	}

	private Boolean mismaGeolocalizacion(Geolocalizacion geo, POI unPoi) {
		return geo.getLatitud() == unPoi.getPoint().getLatitud() && geo.getLongitud() == unPoi.getPoint().getLongitud();
	}

}
