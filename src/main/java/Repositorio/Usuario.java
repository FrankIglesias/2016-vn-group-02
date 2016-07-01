package Repositorio;
import java.util.ArrayList;
import java.util.List;

import AsignarAccionesUsuario.Accion;
import AsignarAccionesUsuario.Criterio;
import DesignDreamTeamLocation.Geolocalizacion;

public class Usuario {
	
	Geolocalizacion point;
	ArrayList<Accion> listaDeAcciones = new ArrayList<Accion>();	
	
	public Usuario(Geolocalizacion point){
		this.point = point;
	}

	public int getComuna() {
		return point.getDomicilio().getComuna();
	}
	
	public void addAccion(Accion accion){
		listaDeAcciones.add(accion);
	}

	public void quitar(Accion accion) {
		if(listaDeAcciones.contains(accion)){
			listaDeAcciones.remove(accion);
		}
	}
	
	public ArrayList<Accion> getListaDeAcciones() {
		return listaDeAcciones;
	}

	public void ingresarALista(List<Criterio> listaDeCriterios, ArrayList<Usuario> filtro) {
		if(listaDeCriterios.stream().allMatch(criterio->criterio.esCumplidoPor(this)))
			filtro.add(this);
	}
}
