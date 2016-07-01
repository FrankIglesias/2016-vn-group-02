package AsignarAccionesUsuario;

import java.util.ArrayList;
import java.util.TimerTask;
import java.util.stream.Collectors;

import DesignDreamTeamProcesses.GestorDeProcesos;
import Repositorio.RepoUsuarios;
import Repositorio.Usuario;

public class AsignarAccionesUsuarios extends TimerTask {

	RepoUsuarios repoUsuario;
	Accion accion;
	Criterio criterio;
	ArrayList<Usuario> usuariosAsignados = new ArrayList<Usuario>();

	public AsignarAccionesUsuarios(RepoUsuarios repoUsuario, Criterio criterio, Accion accion) {
		super();
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
	
	public void ejecutarAccion(Accion unaAccion){
		usuariosAsignados.stream().forEach(unUsuario -> unUsuario.ejecutaUnaAccion(unaAccion));
	}

	@Override
	public void run() {
		System.out.println("Asignando Acciones al usuario.");
		this.asignarAcciones();
		System.out.println("Realizado Correctamente");
		SemVamoASincronizarno_signal();
	}

	private void SemVamoASincronizarno_signal() {
		GestorDeProcesos.sem.release();
	}
}
