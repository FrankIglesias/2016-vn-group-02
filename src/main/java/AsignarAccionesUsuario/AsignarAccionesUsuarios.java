package AsignarAccionesUsuario;

import java.util.ArrayList;
import java.util.List;

import DesignDreamTeamProcesses.DesignDreamTeamProcess;
import Repositorios.RepoUsuarios;
import Repositorios.Usuario;

public class AsignarAccionesUsuarios extends DesignDreamTeamProcess {

	private RepoUsuarios repoUsuario;
	private Accion accion;
	private Criterio criterio;
	private List<Usuario> usuariosAsignados = new ArrayList<Usuario>();

	public AsignarAccionesUsuarios(RepoUsuarios repoUsuario, Criterio criterio, Accion accion) {
		super(null, null); // TODO
		this.repoUsuario = repoUsuario;
		this.accion = accion;
		this.criterio = criterio;
	}

	public ArrayList<Usuario> seleccionarUsuarios(RepoUsuarios repoUsuario) {
		return repoUsuario.seleccionaUsuarios(this);

	}

	public boolean cumpleCriterio(Usuario unUsuario) {
		return criterio.esCumplidoPor(unUsuario);
	}

	public void asignarAcciones() {
		usuariosAsignados = seleccionarUsuarios(repoUsuario);
		usuariosAsignados.forEach(usuario -> usuario.addAccion(accion));
	}

	public void ejecutarAccion(Accion unaAccion) {
		usuariosAsignados.stream().forEach(unUsuario -> unUsuario.ejecutaUnaAccion(unaAccion));
	}

	@Override
	public void run() {
		System.out.println("Asignando Acciones al usuario.");
		this.asignarAcciones();
		System.out.println("Realizado Correctamente");
	}
}
