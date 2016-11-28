package Repositorios;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RepoTerminales {
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

	public void add(Terminal terminal) {

		if (!terminales.contains(terminal)) {
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
