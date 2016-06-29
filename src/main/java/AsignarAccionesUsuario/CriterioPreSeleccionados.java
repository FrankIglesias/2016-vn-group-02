package AsignarAccionesUsuario;
import java.util.ArrayList;

import Repositorio.Usuario;

public class CriterioPreSeleccionados implements Criterio {
	ArrayList<Usuario> usuariosSeleccionados;

	public boolean esCumplidoPor(Usuario unUsuario) {
		return usuariosSeleccionados.stream().anyMatch(seleccionado -> seleccionado == unUsuario);
	}

}
