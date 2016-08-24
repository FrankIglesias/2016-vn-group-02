package Repositorios;

import java.util.ArrayList;
import java.util.stream.Collectors;

import AsignarAccionesUsuario.Accion;
import AsignarAccionesUsuario.AsignarAccionesUsuarios;

public class RepoUsuarios {

	static public ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	static RepoUsuarios instancia = null;
	
	public static RepoUsuarios getInstance() {
		if (instancia == null) {
			instancia = new RepoUsuarios();
			instancia.inicializarListaUsuarios();
		}
		return instancia;
	}

	public void inicializarListaUsuarios() {
		listaUsuarios = new ArrayList<Usuario>();
	}

	public static ArrayList<Usuario> getUsuarios() {
		return listaUsuarios;
	}

	public void add(Usuario usuario) {
		listaUsuarios.add(usuario);
	}

	public boolean todosTienenLaAccion(Accion unaAccion){
		return listaUsuarios.stream().allMatch(unUsuario -> unUsuario.getListaDeAcciones().get(0).equals(unaAccion));
	}
	
	public int size(){
		return listaUsuarios.size();
	}
	
	public ArrayList<Usuario> seleccionaUsuarios(AsignarAccionesUsuarios proceso){
		return listaUsuarios.stream().filter(unUsuario -> proceso.cumpleCriterio(unUsuario))
				.collect(Collectors.toCollection(ArrayList::new));
	}
	//public void filtrarPor(Criterio criterio) {
		//ArrayList<Usuario> filtro = new ArrayList<Usuario>();
		//listaUsuarios.forEach(usuario-> filtro.add(criterio.esCumplidoPor(usuario)));
	//}

}
