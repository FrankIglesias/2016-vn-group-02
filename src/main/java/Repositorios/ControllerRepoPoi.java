package Repositorios;

import java.util.ArrayList;
import java.util.List;

import DesignDreamTeamLocation.Domicilio;
import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamLocation.Localidad;
import TypePois.POI;

public class ControllerRepoPoi {
	
	
	RepoPOIs modeloPOI = RepoPOIs.getInstance();
	RepoTerminales modeloTerminales = RepoTerminales.getInstance();

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
	
	
	public List<Terminal> listarTerminales(int comuna) {

		return modeloTerminales.obtenerTerminales(comuna);
	}
	
	public void eliminarUnaTerminal(Terminal unaTerminal) {
		modeloTerminales.eliminarUnaTerminal(unaTerminal);
	}
	
	public void agregarUnaTerminal(String nombre, int comuna) {
		Terminal unaTerminal = new Terminal(nombre, comuna);
		modeloTerminales.persistirTerminal(unaTerminal);
	}
	
	public void editarUnaTerminal(Terminal terminalVieja, String nombre, int comuna) {
		Terminal unaTerminal;
		
		if(nombre.isEmpty()) 
			unaTerminal = new Terminal(terminalVieja.getNombre(), comuna);
		
		if(comuna == -1)
			unaTerminal = new Terminal(nombre, terminalVieja.comuna);
		else 
			unaTerminal = new Terminal(nombre, comuna);
		
		modeloTerminales.persistirTerminal(unaTerminal);
	}
	
}
