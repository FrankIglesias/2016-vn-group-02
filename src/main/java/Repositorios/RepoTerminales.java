package Repositorios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import AsignarAccionesUsuario.Accion;
import AsignarAccionesUsuario.AsignarAccionesUsuarios;

public class RepoTerminales implements WithGlobalEntityManager, TransactionalOps{
	List<Terminal> terminales;
	Map<String, Integer> reporteBusquedasTotales;
	EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	static RepoTerminales instancia = null;


	public static RepoTerminales getInstance() {
		if (instancia == null) {
			instancia = new RepoTerminales();
			instancia.inicializarRepoTerminales();
		}
		return instancia;
	}

	public void inicializarRepoTerminales() {
		terminales = new ArrayList<Terminal>();
		reporteBusquedasTotales = new HashMap<String, Integer>();
	}

	public void persistirTerminal(Terminal terminal) {
		EntityTransaction transaccion = entityManager.getTransaction();
		transaccion.begin();
		entityManager.persist(terminal);
		transaccion.commit();
	}
		
	public List<Terminal> obtenerTerminales(String nombre, int comuna) {
		if (comuna == -1 && nombre == "") {
			return entityManager().createQuery("from Terminal", Terminal.class).getResultList();
		} else if (nombre == "") {
			return entityManager().createQuery("from Terminal WHERE comuna = :comuna", Terminal.class)
					.setParameter("comuna", comuna).getResultList();
		} else if (comuna == -1){
			return entityManager().createQuery("from Terminal where nombre = :nombreTerminal", Terminal.class).setParameter("nombreTerminal", nombre).getResultList();
		} else {
			
			return entityManager().createQuery("from Terminal where nombre = :nombreTerminal and comuna = :comuna", Terminal.class).setParameter("nombreTerminal", nombre).setParameter("comuna", comuna).getResultList();
		}
	}
	
	public Terminal buscameUnaTerminal(String nombreTerminal) {
		return entityManager().createQuery("from Terminal where nombre = :nombreTerminal", Terminal.class).setParameter("nombreTerminal", nombreTerminal).getResultList().get(0);
	}

	public void eliminarUnaTerminal(Terminal unaTerminal) {
		entityManager().remove(unaTerminal);
		entityManager().flush();
	}

	public void add(Terminal terminal) {
		persistirTerminal(terminal);
	}

	public void addReportesPorTerminal() {
		for (Terminal unaTerminal : terminales) {
			reporteBusquedasTotales.put(unaTerminal.getNombre(), unaTerminal.resultadosTotales());
		}

	}

	public Map<String, Integer> getReporteBusquedasTotales() {
		addReportesPorTerminal();
		return reporteBusquedasTotales;
	}

	public boolean todosTienenLaAccion(Accion unaAccion) {
		return terminales.stream().allMatch(unaTerminal -> unaTerminal.getListaDeAcciones().get(0).equals(unaAccion));
	}

	public int size() {
		return terminales.size();
	}

	public ArrayList<Terminal> seleccionaUsuarios(AsignarAccionesUsuarios proceso) {
		return terminales.stream().filter(unaTerminal -> proceso.cumpleCriterio(unaTerminal))
				.collect(Collectors.toCollection(ArrayList::new));
	}

}
