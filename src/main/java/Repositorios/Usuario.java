package Repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import AsignarAccionesUsuario.Accion;
import AsignarAccionesUsuario.Criterio;
import DesignDreamTeamLocation.Geolocalizacion;


@Entity
@Table(name = "Usuario")
public class Usuario {
	@Id
	private Long id;
	@Transient
	Geolocalizacion point;
	@OneToMany
	List<Accion> listaDeAcciones = new ArrayList<Accion>();

	public void inicializarListaDeAcciones() {
		listaDeAcciones = new ArrayList<Accion>();
	}

	public Usuario(Geolocalizacion point) {
		this.point = point;
	}

	public int getComuna() {
		return point.getDomicilio().getComuna();
	}

	public void addAccion(Accion accion) {
		listaDeAcciones.add(accion);
	}

	public void quitar(Accion accion) {
		
		listaDeAcciones.remove(accion);
	
	}

	public List<Accion> getListaDeAcciones() {
		return listaDeAcciones;
	}

	public void ingresarALista(List<Criterio> listaDeCriterios, ArrayList<Usuario> filtro) {
		if (listaDeCriterios.stream().allMatch(criterio -> criterio.esCumplidoPor(this)))
			filtro.add(this);
	}

	public boolean tieneAccion(Accion accion) {
		return listaDeAcciones.stream().anyMatch(unaAccion -> unaAccion.equals(accion));
	}

	public void ejecutaUnaAccion(Accion unaAccion) {
		unaAccion.ejecutarAccion(this);
	}

}
