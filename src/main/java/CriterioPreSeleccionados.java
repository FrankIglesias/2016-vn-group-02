import java.util.ArrayList;

public class CriterioPreSeleccionados implements Criterios {
	ArrayList<Usuario> usuariosSeleccionados;

	public boolean esCumplidoPor(Usuario unUsuario) {
		return usuariosSeleccionados.stream().anyMatch(seleccionado -> seleccionado == unUsuario);
	}

}
