package AsignarAccionesUsuario;

import Repositorios.Usuario;



public class CriterioComuna implements Criterio {
	int comuna;

	public CriterioComuna(int comuna) {
		super();
		this.comuna = comuna;
	}

	public boolean esCumplidoPor(Usuario unUsuario) {
		if (unUsuario.getComuna() == comuna) {
			return true;
		}
		return false;
	}

}
