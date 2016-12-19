package AsignarAccionesUsuario;
import java.util.List;

import Repositorios.Terminal;


public class CriterioPreSeleccionados implements Criterio {
	List<Terminal> terminalesSeleccionados;

	public CriterioPreSeleccionados(List<Terminal> preSeleccionados) {
		super();
		this.terminalesSeleccionados = preSeleccionados;
	}

	public boolean esCumplidoPor(Terminal unaTerminal) {
		return terminalesSeleccionados.stream().anyMatch(seleccionado -> seleccionado.equals(unaTerminal));
	}

}
