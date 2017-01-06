package Repositorios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import AsignarAccionesUsuario.Accion;
import AsignarAccionesUsuario.Criterio;
import DesignDreamTeamLocation.Geolocalizacion;
import GestorDeMail.GestorDeMailTrucho;
import GestorDeMail.GestorMailInterface;
import TypePois.POI;

@Entity
public class Terminal {
	@Id
	@GeneratedValue
	public Integer id;
	@Column(name = "name_terminal")
	public String nombre;

	@OneToOne(cascade = CascadeType.ALL)
	Geolocalizacion point;
	@OneToMany(cascade = CascadeType.ALL)
	List<Accion> listaDeAcciones = new ArrayList<Accion>();

	@ElementCollection
	@CollectionTable(name = "reporteParcialPorTerminal", joinColumns = @JoinColumn(name = "nombre_terminal"))
	public List<Integer> reporteParcialPorTerminal = new ArrayList<Integer>();

	public Terminal() {
	}

	public Terminal(String nombre) {
		this.nombre = nombre;
	}

	public void setPoint(Geolocalizacion unaGeo) {
		this.point = unaGeo;
	}

	public Terminal(String nombre, int comuna) {
		this.nombre = nombre;
		this.point = new Geolocalizacion();
		this.point.getDomicilio().setComuna(comuna);
	}

	public int getComuna() {
		return this.point.getDomicilio().getComuna();
	}

	public int cantidadDeResultadosPorBusqueda(List<POI> puntosSegunPalabra) {
		return puntosSegunPalabra.size();
	}

	public void addResultadosParcialesAlReporte(List<POI> puntosSegunPalabra) {
		reporteParcialPorTerminal.add(cantidadDeResultadosPorBusqueda(puntosSegunPalabra));
	}

	public List<Integer> getReporteParcialPorTerminal() {
		return reporteParcialPorTerminal;
	}

	public Geolocalizacion getPoint() {
		return this.point;

	}

	public int getId() {
		return this.id;
	}

	public int sumarResultados(List<Integer> enteros) {
		int resultado = 0;
		for (Integer entero : enteros) {
			resultado += entero;
		}
		return resultado;
	}

	public int resultadosTotales() {
		return this.sumarResultados(reporteParcialPorTerminal);
	}

	public String getNombre() {
		return nombre;
	}

	public void addAccion(Accion accion) {
		if (!listaDeAcciones.contains(accion))
			listaDeAcciones.add(accion);
	}

	public void quitar(Accion accion) {
		listaDeAcciones.remove(accion);
	}

	public List<Accion> getListaDeAcciones() {
		return listaDeAcciones;
	}
	public void ingresarALista(List<Criterio> listaDeCriterios, ArrayList<Terminal> filtro) {
		if (listaDeCriterios.stream().allMatch(criterio -> criterio.esCumplidoPor(this)))
			filtro.add(this);
	}

	public boolean tieneAccion(Accion accion) {
		return listaDeAcciones.stream().anyMatch(unaAccion -> unaAccion.equals(accion));
	}

	public void ejecutaUnaAccion(Accion unaAccion) {
		unaAccion.ejecutarAccion(this);
	}

	public void ejecutarTodasLasAcciones() {
		listaDeAcciones.forEach(unaAccion -> unaAccion.ejecutarAccion(this));
	}

	public List<String> getNombreDeAcciones() {
		List<String> listaADevolver = new ArrayList<String>();
		listaDeAcciones.stream().forEach(unaAccion -> listaADevolver.add(unaAccion.getNombre()));
		return listaADevolver;
	}
}
