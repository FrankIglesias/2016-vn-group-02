package AsignarAccionesUsuario;
import java.util.ArrayList;

import Repositorio.Usuario;

public class CriterioPreSeleccionados implements Criterios {
	ArrayList<Usuario> usuariosSeleccionados;

	public boolean esCumplidoPor(Usuario unUsuario) {
		return usuariosSeleccionados.stream().anyMatch(seleccionado -> seleccionado == unUsuario);
	}

}
