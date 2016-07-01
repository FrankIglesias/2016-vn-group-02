package AsignarAccionesUsuario;

import Repositorio.Usuario;

public class AccionDesactivar implements Accion {
	Accion accion;

	public AccionDesactivar(Accion accion) {
		super();
		this.accion = accion;
	}

	public void ejecutarAccion(Usuario usuario) {
		usuario.quitar(accion);
	}

}
