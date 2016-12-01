package Repositorios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import AsignarAccionesUsuario.Accion;
import TypePois.POI;

public class RepoDeBusquedas implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

	private static RepoDeBusquedas instancia = null;
	List<Busqueda> busquedas;
	Map<LocalDate, Integer> reportePorFecha;
	RepoTerminales repoTerminales;

	public void persistirBusqueda(Busqueda unObjeto) {
		withTransaction(() -> {
			persist(unObjeto);
		});
	}

	// TODO abstraer estas cosas a una clase que se encargue de persistir todo
	public void persistirAccion(Accion unObjeto) {
		entityManager().persist(unObjeto);

	}

	public Accion obtenerObjetoAccion(Integer id) {
		return entityManager().find(Accion.class, id);
	}

	public Busqueda obtenerUnaBusqueda(Integer id) {
		return entityManager().find(Busqueda.class, id);
	}

	public List<Busqueda> listar() {
		return entityManager()//
				.createQuery("from Consultora", Busqueda.class) //
				.getResultList();
	}

	public static RepoDeBusquedas getInstance() {
		if (instancia == null) {
			instancia = new RepoDeBusquedas();
			instancia.inicializarBaseDeDatos();
		}
		return instancia;
	}

	public List<Busqueda> getBusquedas() {
		return busquedas;
	}

	public void inicializarBaseDeDatos() {
		busquedas = new ArrayList<Busqueda>();
		reportePorFecha = new HashMap<LocalDate, Integer>();
		repoTerminales = RepoTerminales.getInstance();
	}

	public Busqueda addBusqueda(Terminal terminal, String frase, double tiempo, double tiempoMax,
			List<POI> puntosObtenidos) {
		Busqueda busqueda = new Busqueda(terminal, frase, tiempo, tiempoMax, puntosObtenidos);
		busquedas.add(busqueda);
		addBusquedasPorFechaAlReporte(busqueda.getFecha());
		repoTerminales.add(terminal);
		persistirBusqueda(busqueda);
		return busqueda;
	}

	public int cantidadDeBusquedasPorFecha(LocalDate fecha) {
		return busquedas.stream().filter(unaBusqueda -> unaBusqueda.esDeLaFecha(fecha)).collect(Collectors.toList())
				.size();
	}

	public void addBusquedasPorFechaAlReporte(LocalDate fecha) {
		reportePorFecha.put(fecha, this.cantidadDeBusquedasPorFecha(fecha));
	}

	public Map<LocalDate, Integer> getReportePorFecha() {
		return reportePorFecha;
	}

}
