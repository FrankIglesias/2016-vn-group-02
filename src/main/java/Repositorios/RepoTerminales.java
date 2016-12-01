package Repositorios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

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
