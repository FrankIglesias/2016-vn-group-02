package Controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import DesignDreamTeamLocation.Domicilio;
import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamLocation.Localidad;
import Repositorios.RepoPOIs;
import TypePois.POI;

public class ControllerRepoPoi implements WithGlobalEntityManager, TransactionalOps {

	private static ControllerRepoPoi instancia;
	RepoPOIs modeloPOI = RepoPOIs.getInstance();

	public static ControllerRepoPoi getInstance() {
		if (instancia == null) {
			instancia = new ControllerRepoPoi();
		}
		return instancia;
	}

	public List<POI> listarPOIsParaAdmin(String nombre, String tipo) {

		List<POI> listadoDePOIs = new ArrayList<POI>();

		if (!tipo.isEmpty()) {
			listadoDePOIs.addAll(modeloPOI.obtenerDeHibernateSegunPalabrasClave(tipo));
		}
		if (!nombre.isEmpty()) {
			String[] linea = nombre.split(" ");
			ArrayList<String> palabras = new ArrayList<String>(Arrays.asList(linea));
			palabras.forEach(
					palabrita -> listadoDePOIs.addAll(modeloPOI.obtenerDeHibernateSegunPalabrasClave(palabrita)));
		}

		if (nombre.isEmpty() && tipo.isEmpty()) {
			listadoDePOIs.addAll(modeloPOI.levantarTodoDeHibernate());
		}

		return listadoDePOIs;
	}

	public void editarUnPOI(POI unPoi, String nombre, String callePrincipal, String entreCalles, String altura,
			String piso, String unidad, String codigoPostal, int comuna, String unaCiudad, String unaProvincia,
			String unPais, double latitud, double longitud) {

		Domicilio unDomi = new Domicilio(callePrincipal, entreCalles, altura, piso, unidad, codigoPostal, comuna);
		Localidad unaLoca = new Localidad(unaCiudad, unaProvincia, unPais);
		Geolocalizacion unaGeo = new Geolocalizacion(latitud, longitud, unDomi, unaLoca);
		modeloPOI.modificarUnPoi(unPoi, nombre, unaGeo);

	}

	public void borrarUnPOIporId(String id) {
			POI poiABorrar = modeloPOI.obtenerDeHibernateSegunId(id);
			modeloPOI.borrarDeMongo(poiABorrar);
			modeloPOI.borrarDeHibernate(poiABorrar);
			
	}

	public HashMap<String, Integer> cargarComunas() {
		HashMap<String, Integer> comunas = new HashMap<String, Integer>();
		comunas.put("Agronomía", 15);
		comunas.put("Almagro", 5);
		comunas.put("Balvanera", 3);
		comunas.put("Barracas", 4);
		comunas.put("Belgrano", 13);
		comunas.put("Boedo", 5);
		comunas.put("Caballito", 6);
		comunas.put("Chacarita", 15);
		comunas.put("Coghlan", 12);
		comunas.put("Colegiales", 13);

		comunas.put("Constitución", 1);
		comunas.put("Flores", 7);
		comunas.put("Floresta", 10);
		comunas.put("La Boca", 4);
		comunas.put("La Paternal", 15);
		comunas.put("Liniers", 9);
		comunas.put("Mataderos", 9);
		comunas.put("Monte Castro", 10);
		comunas.put("Monserrat", 1);
		comunas.put("Nueva Pompeya", 4);
		comunas.put("Núñez", 13);

		comunas.put("Palermo", 14);
		comunas.put("Parque Avellaneda", 9);
		comunas.put("Parque Chacabuco", 7);
		comunas.put("Parque Chas", 15);
		comunas.put("Parque Patricios", 4);
		comunas.put("Puerto Madero", 1);
		comunas.put("Recoleta", 2);
		comunas.put("Retiro", 1);
		comunas.put("Saavedra", 12);
		comunas.put("San Cristobal", 3);

		comunas.put("San Nicolas", 1);
		comunas.put("San Telmo", 1);
		comunas.put("Vélez Sársfield", 10);
		comunas.put("Versalles", 10);
		comunas.put("Villa Crespo", 15);
		comunas.put("Villa del Parque", 11);
		comunas.put("Villa Devoto", 11);
		comunas.put("Villa General Mitre", 11);
		comunas.put("Villa Lugano", 8);
		comunas.put("Villa Luro", 10);

		comunas.put("Villa Ortúzar", 15);
		comunas.put("Villa Pueyrredón", 12);
		comunas.put("Villa Real", 10);
		comunas.put("Villa Riachuelo", 8);
		comunas.put("Villa Santa Rita", 11);
		comunas.put("Villa Soldati", 8);
		comunas.put("Villa Urquiza", 12);

		return comunas;

	}

}
