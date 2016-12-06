package Repositorios;


import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import DesignDreamTeamLocation.Geolocalizacion;
import TypePois.Banco;
import TypePois.CGP;
import TypePois.Local;
import TypePois.POI;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
public class RepoPOIs implements WithGlobalEntityManager {

	List<POI> puntosDeIntereses;
	static RepoPOIs instancia;
	EntityManager entityManager = PerThreadEntityManagers.getEntityManager();

	public void persistirEnHibernate(POI unPOI)
	{
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
		return entityManager() 
		        .createQuery("from POI p join p.palabrasClave pc  WHERE pc = :palabraClave", POI.class) 
		        .setParameter("palabraClave", palabraClave) 
		        .getResultList();
	}
	
	public String mappearUnPoi(POI unPoi) throws JsonProcessingException {
		ObjectMapper map = new ObjectMapper();
		return map.writeValueAsString(unPoi);
	}
	
	public void persistirEnMongo(POI unPOI) {
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

//	public POI obtenerDeMongo(int id) {
//		
//	}
//	
	
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

	public void agregarNuevosPoi(POI nuevoPOI) {
		puntosDeIntereses.add(nuevoPOI);
	}

	public void sacarPoi(POI POIaSacar) {
		puntosDeIntereses.remove(puntosDeIntereses.stream().filter(unPoi -> sonIguales(unPoi, POIaSacar))
				.collect(Collectors.toList()).get(0));

	}

	public int cantidadDePOI() {
		return puntosDeIntereses.size();
	}

	private boolean sonIguales(POI point1, POI point2) {
		return point1.getPoint().getLatitud() == point2.getPoint().getLatitud()
				&& point1.getPoint().getLongitud() == point2.getPoint().getLongitud();
		// dos point son iguales si estan exactamente en el mismo punto.
	}

	public void agregarVariosPoi(List<POI> listaDePoi) {
		puntosDeIntereses.addAll(listaDePoi);

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

	public void actualizarLocal(String nombre, ArrayList<String> palabrasClave) {

		POI localAModificar = this.tieneUnLocalConNombre(nombre).get(0);
		puntosDeIntereses.remove(localAModificar);

		localAModificar.setPalabrasClave(palabrasClave);
		puntosDeIntereses.add(localAModificar);

	}

	public boolean isEmpty() {
		return puntosDeIntereses.isEmpty();
	}

	public int size() {
		return puntosDeIntereses.size();
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
