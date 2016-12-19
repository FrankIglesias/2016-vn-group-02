package Repositorios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import AsignarAccionesUsuario.Accion;
import AsignarAccionesUsuario.Criterio;
import DesignDreamTeamLocation.Geolocalizacion;
import GestorDeMail.GestorDeMailTrucho;
import GestorDeMail.GestorMailInterface;
import TypePois.POI;

@Entity
@Table(name = "Terminal")
public class Terminal {
	@Id
	@GeneratedValue
	Integer id;
	@Column(name = "name_terminal")
	private String nombre; 

	@OneToOne
	Geolocalizacion point = new Geolocalizacion();
	@OneToMany
	List<Accion> listaDeAcciones = new ArrayList<Accion>();
	
	@ElementCollection
	@CollectionTable(name = "reporteParcialPorTerminal", joinColumns = @JoinColumn(name = "nombre_terminal"))
	private List<Integer> reporteParcialPorTerminal = new ArrayList<Integer>();

	@Transient
	private GestorMailInterface gestorDeMail = GestorDeMailTrucho.getInstance();

	private String mailAdmin = "mailprueba@gmail.com";

	protected Terminal() {
	}

	public Terminal(String nombre) {
		this.nombre = nombre;
	}
	
	public Terminal(String nombre, int comuna) {
		this.nombre = nombre;
		this.point.getDomicilio().setComuna(comuna);
	}
	
	public int getComuna() {
		return this.point.getDomicilio().getComuna();
	}
	
	public void setGestorDeMail(GestorMailInterface gestorDeMail) {
		this.gestorDeMail = gestorDeMail;
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

	public boolean enviarMailAlAdmin(String frase, LocalDateTime fecha, String terminal) {
		return gestorDeMail.enviarMail(Message.RecipientType.TO, mailAdmin, frase, terminal);
	}

	public String getNombre() {
		return nombre;
	}

	public GestorMailInterface getGestor() {
		return gestorDeMail;
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
}
