package Repositorio;
import java.util.ArrayList;

import AsignarAccionesUsuario.Accion;
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
	
	public void addAccion(ArrayList<Accion> acciones){
		listaDeAcciones.addAll(acciones);
	}

	public void quitar(Accion accion) {
		if(listaDeAcciones.contains(accion)){
			listaDeAcciones.remove(accion);
		}
	}
	
	
}
