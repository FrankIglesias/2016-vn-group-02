package AsignarAccionesUsuario;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;

import Repositorio.GestorDeProcesos;
import Repositorio.RepoUsuarios;
import Repositorio.Usuario;

public class AsignarAccionesUsuarios extends TimerTask {

	RepoUsuarios repoUsuario;
	Accion accion;
	Criterio criterio;

	public AsignarAccionesUsuarios(RepoUsuarios repoUsuario, Criterio criterio, Accion accion) {
		super();
		this.repoUsuario = repoUsuario;
		this.accion = accion;
		this.criterio = criterio;
	}

	public ArrayList<Usuario> seleccionarUsuarios(RepoUsuarios repoUsuario) {
		return RepoUsuarios.getUsuarios().stream().filter(unUsuario -> cumpleCriterio(unUsuario))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public boolean cumpleCriterio(Usuario unUsuario) {
		return criterio.esCumplidoPor(unUsuario);
	}

	public void asignarAcciones() {
		ArrayList<Usuario> usuarios = seleccionarUsuarios(repoUsuario);
		usuarios.forEach(usuario -> usuario.addAccion(accion));
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
