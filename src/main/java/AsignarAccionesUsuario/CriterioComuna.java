package AsignarAccionesUsuario;

import Repositorios.Terminal;



public class CriterioComuna implements Criterio {
	int comuna;

	public CriterioComuna(int comuna) {
		super();
		this.comuna = comuna;
	}

	public boolean esCumplidoPor(Terminal unaTerminal) {
		if (unaTerminal.getComuna() == comuna) {
			return true;
		}
		return false;
	}


}
