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
	ArrayList<Accion> listaDeAcciones = new ArrayList<Accion>();
	ArrayList<Criterio> listaDeCriterios = new ArrayList<Criterio>();

	public AsignarAccionesUsuarios(RepoUsuarios repoUsuario, ArrayList<Accion> listaDeAcciones,
			ArrayList<Criterio> listaDeCriterios) {
		super();
		this.repoUsuario = repoUsuario;
		this.listaDeAcciones = listaDeAcciones;
		this.listaDeCriterios = listaDeCriterios;
	}

	public ArrayList<Usuario> seleccionarUsuarios(RepoUsuarios repoUsuario) {
		return RepoUsuarios.getUsuarios().stream().filter(unUsuario -> cumpleCriterios(unUsuario))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public boolean cumpleCriterios(Usuario unUsuario) {
		if(listaDeCriterios.isEmpty())
			System.out.println("ListaDeCriterios vacia");
		return this.listaDeCriterios.stream().allMatch(unCriterio -> unCriterio.esCumplidoPor(unUsuario));
	}

	public void asignarAcciones(RepoUsuarios repoUsuarios) {
		ArrayList<Usuario> usuarios = seleccionarUsuarios(repoUsuarios);
		usuarios.forEach(usuario -> usuario.addAccion(listaDeAcciones));
	}

	@Override
	public void run() {
		System.out.println("Asignando Acciones al usuario.");
		this.asignarAcciones(repoUsuario);
		System.out.println("Realizado Correctamente");
		SemVamoASincronizarno_signal();
	}

	private void SemVamoASincronizarno_signal() {
		GestorDeProcesos.sem.release();
	}
}
