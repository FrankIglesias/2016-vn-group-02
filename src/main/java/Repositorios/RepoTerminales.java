package Repositorios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import AsignarAccionesUsuario.Accion;
import AsignarAccionesUsuario.AsignarAccionesUsuarios;

public class RepoTerminales implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
	List<Terminal> terminales;
	Map<String, Integer> reporteBusquedasTotales;
	static RepoTerminales instancia;

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
		withTransaction(() -> {
			persist(terminal);
		});

		System.out.println("fjadfiojadifo");
	}

	public List<Terminal> obtenerTerminales(int comuna) {

		if (comuna != -1) {
			return entityManager().createQuery("from Terminal", Terminal.class).getResultList();
		} else {
			return entityManager().createQuery("from Terminal WHERE comuna = :comuna", Terminal.class)
					.setParameter("comuna", comuna).getResultList();
		}

	}

	public void eliminarUnaTerminal(Terminal unaTerminal) {
		entityManager().remove(unaTerminal);
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
