package Controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DesignDreamTeamLocation.Domicilio;
import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamLocation.Localidad;
import Repositorios.RepoPOIs;
import TypePois.POI;

public class ControllerRepoPoi {
	
	
	private static ControllerRepoPoi instancia;
	RepoPOIs modeloPOI = RepoPOIs.getInstance();
	
	public static ControllerRepoPoi getInstance() {
		if (instancia == null) {
			instancia = new ControllerRepoPoi ();
		}
		return instancia;
	}
	

	public List<POI> listarPOIsParaAdmin(String nombre, String tipo) {

		List<POI> listadoDePOIs = new ArrayList<POI>();
		
		if(!tipo.isEmpty()) {
			listadoDePOIs.addAll(modeloPOI.obtenerDeHibernateSegunPalabrasClave(tipo));
		}
		if(!nombre.isEmpty()) {
			String[] linea = nombre.split(" ");
			ArrayList<String> palabras = new ArrayList<String>(Arrays.asList(linea));
			palabras.forEach(palabrita -> listadoDePOIs.addAll(modeloPOI.obtenerDeHibernateSegunPalabrasClave(palabrita)));
		}
		
		if(nombre.isEmpty() && tipo.isEmpty()) {
			listadoDePOIs.addAll(modeloPOI.levantarTodoDeHibernate());
		}
		
		return listadoDePOIs;
	}
	
	public void editarUnPOI(POI unPoi, String nombre, String callePrincipal, String entreCalles, String altura, String piso, String unidad,
		String codigoPostal,int comuna, String unaCiudad, String unaProvincia, String unPais, double latitud, double longitud) 
	{
		
		Domicilio unDomi = new Domicilio(callePrincipal, entreCalles, altura, piso, unidad, codigoPostal, comuna);
		Localidad unaLoca = new Localidad(unaCiudad, unaProvincia, unPais);
		Geolocalizacion unaGeo = new Geolocalizacion(latitud, longitud, unDomi, unaLoca);
		modeloPOI.modificarUnPoi(unPoi, nombre, unaGeo);
		
	}
	
	public void borrarUnPOIporId(String id) 
		{
			POI poiABorrar = modeloPOI.obtenerDeHibernateSegunId(id);
			modeloPOI.borrarDeMongo(poiABorrar);
			modeloPOI.borrarDeHibernate(poiABorrar);	
		}

}
