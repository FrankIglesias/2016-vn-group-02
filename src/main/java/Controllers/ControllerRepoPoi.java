package Controllers;

import java.util.ArrayList;
import java.util.List;

import DesignDreamTeamLocation.Domicilio;
import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamLocation.Localidad;
import Repositorios.RepoPOIs;
import Repositorios.RepoTerminales;
import Repositorios.Terminal;
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
			listadoDePOIs.addAll(modeloPOI.obtenerDeMongoSegunPalabrasClave(tipo));
		}
		if(!nombre.isEmpty()) {
			listadoDePOIs.addAll(modeloPOI.obtenerDeHibernateSegunPalabrasClave(nombre));
			listadoDePOIs.addAll(modeloPOI.obtenerDeMongoSegunPalabrasClave(nombre));
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
	
	public void borrarUnPOI(POI unPoi) 
		{
			modeloPOI.borrarDeMongo(unPoi);
			modeloPOI.borrarDeHibernate(unPoi);	
		}

}
