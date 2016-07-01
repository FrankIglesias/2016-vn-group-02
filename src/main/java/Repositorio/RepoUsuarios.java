package Repositorio;

import java.util.ArrayList;
import java.util.List;

import AsignarAccionesUsuario.Criterio;

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

	public ArrayList<Usuario> filtrarPor(List<Criterio> listaDeCriterios) {
		ArrayList<Usuario> filtro = new ArrayList<Usuario>();
		listaUsuarios.forEach(usuario-> usuario.ingresarALista(listaDeCriterios,filtro));
		return null;
	}

}
