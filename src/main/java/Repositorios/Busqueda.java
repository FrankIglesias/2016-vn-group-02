package Repositorios;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.uqbarproject.jpa.java8.extras.convert.LocalDateTimeConverter;

@Entity
@Table(name = "Busqueda")
public class Busqueda {

	@Id
	@GeneratedValue
	private Integer id;

	@ElementCollection
	List<Integer> idsPuntosObtenidos;
	@Convert(converter = LocalDateTimeConverter.class)
	LocalDateTime fecha;
	String nombreTerminal;
	String frase;
	double tiempo;

	double tiempoMax;

	protected Busqueda() {
	}

	public Busqueda(String terminal, String frase, double tiempo, double tiempoMax, List<Integer> puntosObtenidos) {
		this.nombreTerminal = terminal;
		this.fecha = LocalDateTime.now();
		this.frase = frase;
		this.tiempo = tiempo;
		this.tiempoMax = tiempoMax;
		this.idsPuntosObtenidos = puntosObtenidos;
		this.analizaElTiempoDeBusqueda();
	}

	public Integer getId() {
		return id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public String getTerminal() {
		return nombreTerminal;
	}

	public boolean esDeLaFecha(LocalDateTime fecha) {
		return this.fecha.isEqual(fecha);
	}

	public void analizaElTiempoDeBusqueda() {
		if (tiempo >= tiempoMax) {
			System.out.println(nombreTerminal + " " + tiempo + " " + tiempoMax + " " + fecha);
			RepoTerminales.getInstance().enviarMailAlAdmin("El tiempo de busqueda se ha excedido en " + (tiempo - tiempoMax), fecha,
					nombreTerminal);
		}

	}

	public boolean estaEntreDosFechas(LocalDateTime antes, LocalDateTime despues) {
		return (fecha.isAfter(antes) && fecha.isBefore(despues));
	}

	public List<Integer> getPuntosBuscados() {
		return idsPuntosObtenidos;
	}

	public int getCantidadDePoisObtenidos() {
		return idsPuntosObtenidos.size();
	}

}
