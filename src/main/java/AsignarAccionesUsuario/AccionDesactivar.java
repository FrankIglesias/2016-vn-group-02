package AsignarAccionesUsuario;

import Repositorios.Usuario;

public class AccionDesactivar implements Accion {
	Accion accionAquitar;

	public AccionDesactivar(Accion accion) {
		super();
		this.accionAquitar = accion;
	}

	public void ejecutarAccion(Usuario usuario) {
			usuario.quitar(accionAquitar);
			usuario.quitar(this);
	}

}
