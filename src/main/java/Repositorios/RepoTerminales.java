package Repositorios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import TypePois.POI;

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
	
	public void persistirTerminal(Terminal terminal){
	withTransaction(() -> {
		persist(terminal);});
	
}
	
	public List<Terminal> obtenerTerminales(int comuna) {
		
		if(comuna != -1) {
			return entityManager().createQuery("from Terminal", Terminal.class).getResultList();
		}
		else {
			return entityManager().createQuery("from Terminal WHERE comuna = :comuna", Terminal.class).setParameter("comuna", comuna).getResultList();
		}
		
	}
	
	public void eliminarUnaTerminal(Terminal unaTerminal) {
		entityManager().remove(unaTerminal);
	}
	
	public void add(Terminal terminal) {

		if (!terminales.contains(terminal)) {
			persistirTerminal(terminal);
			terminales.add(terminal);
		}
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

}
