import java.util.ArrayList;

import javax.management.remote.NotificationResult;

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
import DesignDreamTeamLocation.Domicilio;
import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamLocation.Localidad;
import Repositorios.RepoUsuarios;
import Repositorios.Usuario;

public class TestAsignarAccionesUsuario {

	Usuario usuario1;
	Usuario usuario2;
	Usuario usuario3;
	RepoUsuarios repoUsuarios;
	ArrayList<Usuario> preSeleccionados = new ArrayList<Usuario>();

	Geolocalizacion geolocalizacion;
	Domicilio domicilio1;
	Domicilio domicilio2;

	AccionDesactivar accionDesactivar;
	AccionNotificarAdmin accionNotificarAdmin;

	CriterioComuna criterioComuna;
	CriterioComuna criterioComuna2;
	CriterioPreSeleccionados criterioPreSeleccionados;
	CriterioTodosUsuarios criterioTodosUsuarios;

	@Before
	public void init() {
		domicilio1 = new Domicilio("", "", "", "", "", "1111", 3);
		domicilio2 = new Domicilio("", "", "", "", "", "1111", 1);
		usuario1 = new Usuario(new Geolocalizacion(0, 0, domicilio1, new Localidad("", "", "")));
		usuario2 = new Usuario(new Geolocalizacion(0, 0, domicilio2, new Localidad("", "", "")));
		usuario3 = new Usuario(new Geolocalizacion(0, 0, domicilio2, new Localidad("", "", "")));
		repoUsuarios = RepoUsuarios.getInstance();
		repoUsuarios.inicializarListaUsuarios();
		repoUsuarios.add(usuario1);
		repoUsuarios.add(usuario2);
		repoUsuarios.add(usuario3);
		preSeleccionados.add(usuario3);

		accionNotificarAdmin = new AccionNotificarAdmin("mailprueba@gmail.com");
		accionDesactivar = new AccionDesactivar(accionNotificarAdmin);
		criterioComuna = new CriterioComuna(1);
		criterioComuna2 = new CriterioComuna(15);
		criterioPreSeleccionados = new CriterioPreSeleccionados(preSeleccionados);
		criterioTodosUsuarios = new CriterioTodosUsuarios();
	}

	@Test
	public void asignarAccionAPreseleccionadosTest() {
		AsignarAccionesUsuarios proceso = new AsignarAccionesUsuarios(repoUsuarios, criterioPreSeleccionados,
				accionNotificarAdmin);
		proceso.asignarAcciones();
		Assert.assertTrue(usuario3.getListaDeAcciones().get(0).equals(accionNotificarAdmin));
	}

	@Test
	public void asignarAccionATodosLosUsuarioTest() {
		AsignarAccionesUsuarios proceso = new AsignarAccionesUsuarios(repoUsuarios, criterioTodosUsuarios,
				accionNotificarAdmin);
		proceso.asignarAcciones();
		
		Assert.assertTrue(repoUsuarios.todosTienenLaAccion(accionNotificarAdmin));

	}
	
	@Test
	public void desactivarAccionAUsuarioPreseleccionadoTest() {
		usuario3.inicializarListaDeAcciones();
		
		AsignarAccionesUsuarios proceso = new AsignarAccionesUsuarios(repoUsuarios, criterioPreSeleccionados,
				accionNotificarAdmin);
		proceso.asignarAcciones();
		
		AsignarAccionesUsuarios proceso2 = new AsignarAccionesUsuarios(repoUsuarios, criterioPreSeleccionados,
			accionDesactivar);
		proceso2.asignarAcciones();
		proceso2.ejecutarAccion(accionDesactivar);
		
		Assert.assertTrue(usuario3.getListaDeAcciones().isEmpty());

	}

}