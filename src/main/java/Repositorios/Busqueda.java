package Repositorios;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.uqbarproject.jpa.java8.extras.convert.LocalDateTimeConverter;

import TypePois.POI;

@Entity
@Table(name = "Busqueda")
public class Busqueda {

	@Id
	@GeneratedValue
	private Integer id;

	@OneToMany(cascade = CascadeType.ALL)
	List<POI> puntosObtenidos;
	@Convert(converter = LocalDateTimeConverter.class)
	LocalDateTime fecha;
	@ManyToOne(cascade = CascadeType.MERGE)
	Terminal terminal;
	String frase;
	double tiempo;

	double tiempoMax;

	protected Busqueda() {
	}

	public Busqueda(Terminal terminal, String frase, double tiempo, double tiempoMax, List<POI> puntosObtenidos) {
		this.terminal = terminal;
		this.fecha = LocalDateTime.now();
		this.frase = frase;
		this.tiempo = tiempo;
		this.tiempoMax = tiempoMax;
		this.puntosObtenidos = puntosObtenidos;
		this.analizaElTiempoDeBusqueda();
	}

	public Integer getId() {
		return id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public Terminal getTerminal() {
		return terminal;
	}

	public boolean esDeLaFecha(LocalDateTime fecha) {
		return this.fecha.isEqual(fecha);
	}

	public void analizaElTiempoDeBusqueda() {
		if (tiempo >= tiempoMax) {
			System.out.println(terminal.getNombre() + " " + tiempo + " " + tiempoMax + " " + fecha);
			terminal.enviarMailAlAdmin("El tiempo de busqueda se ha excedido en " + (tiempo - tiempoMax), fecha,
					terminal.getNombre());
		}

	}

	public boolean estaEntreDosFechas(LocalDateTime antes, LocalDateTime despues) {
		return (fecha.isAfter(antes) && fecha.isBefore(despues));
	}

	public List<POI> getPuntosBuscados() {
		return puntosObtenidos;
	}

	public int getCantidadDePoisObtenidos() {
		return puntosObtenidos.size();
	}

}
