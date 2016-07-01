import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import AsignarAccionesUsuario.Accion;
import AsignarAccionesUsuario.AccionDesactivar;
import AsignarAccionesUsuario.AccionNotificarAdmin;
import AsignarAccionesUsuario.AsignarAccionesUsuarios;
import AsignarAccionesUsuario.Criterio;
import AsignarAccionesUsuario.CriterioComuna;
import AsignarAccionesUsuario.CriterioPreSeleccionados;
import AsignarAccionesUsuario.CriterioTodosUsuarios;
import Repositorio.RepoUsuarios;
import Repositorio.Usuario;

public class AsignarAccionesTest {
	
	AsignarAccionesUsuarios proceso;
	AccionDesactivar accionDesactivar;
	AccionNotificarAdmin accionNotificarAdmin;
	CriterioComuna criterioComuna;
	CriterioPreSeleccionados criterioPreSeleccionados;
	CriterioTodosUsuarios criterioTodosUsuarios;
	
	@Before
	public void init() {
		accionDesactivar = new AccionDesactivar(accionDesactivar);
		
	}
	
	@Test
	public void AsignarAccionUsuarioTest(){
	}
	@Test
	public void AsignarAccionUsuarioListaVaciaTest(){
	}
	@Test
	public void seleccionarUsuariosTest(){
	}
}

public AsignarAccionesUsuarios(RepoUsuarios repoUsuario, ArrayList<Accion> listaDeAcciones,
		List<Criterio> listaDeCriterios) {
	super();
	this.repoUsuario = repoUsuario;
	this.listaDeAcciones = listaDeAcciones;
	this.listaDeCriterios = listaDeCriterios;
}

ArrayList<Usuario> seleccionarUsuarios(RepoUsuarios repoUsuario) {
	return repoUsuario.getUsuarios().stream()
			.filter(unUsuario -> listaDeCriterios.stream()
					.allMatch(unCriterio -> unCriterio.esCumplidoPor(unUsuario)))
			.collect(Collectors.toCollection(ArrayList::new));
}

public void asignarAcciones(RepoUsuarios repoUsuarios){
	ArrayList<Usuario> usuarios = seleccionarUsuarios(repoUsuarios);
	usuarios.forEach(usuario-> usuario.addAccion(listaDeAcciones));
}

