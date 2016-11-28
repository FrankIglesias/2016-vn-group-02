package Repositorios;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.uqbarproject.jpa.java8.extras.convert.LocalDateConverter;

import TypePois.POI;


@Entity
@Table(name = "Busqueda")
public class Busqueda {

	@Id
	@GeneratedValue
	private Integer id;

	@Transient
	List<POI> puntosObtenidos;
	@Convert(converter = LocalDateConverter.class)
	LocalDate fecha;
	@ManyToOne
	Terminal terminal;
	String frase;
	double tiempo;

	@Transient
	double tiempoMax;

	public Busqueda(Terminal terminal, String frase, double tiempo, double tiempoMax, List<POI> puntosObtenidos) {
		this.terminal = terminal;
		this.fecha = LocalDate.now();
		this.frase = frase;
		this.tiempo = tiempo;
		this.tiempoMax = tiempoMax;
		this.puntosObtenidos = puntosObtenidos;
		this.analizaElTiempoDeBusqueda();
	}

	public Integer getId() {
		return id;
	}

	public boolean esDeLaFecha(LocalDate fecha) {
		return this.fecha.isEqual(fecha);
	}

	public void analizaElTiempoDeBusqueda() {
		if (tiempo >= tiempoMax) {
			terminal.avisaAlAdminTiempoExcedido((tiempoMax - tiempo), frase, fecha, terminal.getNombre());
		}

	}

	public LocalDate getFecha() {
		return fecha;
	}
}
