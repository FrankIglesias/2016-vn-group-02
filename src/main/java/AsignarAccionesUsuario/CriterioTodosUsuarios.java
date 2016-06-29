package AsignarAccionesUsuario;

import Repositorio.Usuario;

public class CriterioTodosUsuarios implements Criterio {

	public boolean esCumplidoPor(Usuario unUsuario) {
		return true;
	}
}
