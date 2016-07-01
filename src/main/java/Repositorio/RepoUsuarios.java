package Repositorio;

import java.util.ArrayList;

public class RepoUsuarios {

	static public ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	static RepoUsuarios instancia;

	
	
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

}
