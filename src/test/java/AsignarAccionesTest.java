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
import DesignDreamTeamLocation.Domicilio;
import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamLocation.Localidad;
import Repositorio.RepoUsuarios;
import Repositorio.Usuario;
import TypePois.POI;

public class AsignarAccionesTest {

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
	ArrayList<Accion> acciones = new ArrayList<Accion>();

	CriterioComuna criterioComuna;
	CriterioComuna criterioComuna2;
	CriterioPreSeleccionados criterioPreSeleccionados;
	CriterioTodosUsuarios criterioTodosUsuarios;
	ArrayList<Criterio> criterios = new ArrayList<Criterio>();

	AsignarAccionesUsuarios proceso;

	@Before
	public void init() {
		domicilio1 = new Domicilio("", "", "", "", "", "1111", 3);
		domicilio2 = new Domicilio("", "", "", "", "", "1111", 1);
		usuario1 = new Usuario(new Geolocalizacion(0, 0, domicilio1, new Localidad("", "", "")));
		usuario2 = new Usuario(new Geolocalizacion(0, 0, domicilio2, new Localidad("", "", "")));
		usuario3 = new Usuario(new Geolocalizacion(0, 0, domicilio2, new Localidad("", "", "")));
		repoUsuarios = repoUsuarios.getInstance();
		repoUsuarios.add(usuario1);
		repoUsuarios.add(usuario2);
		repoUsuarios.add(usuario3);
		preSeleccionados.add(usuario3);

		accionDesactivar = new AccionDesactivar(accionDesactivar);
		accionNotificarAdmin = new AccionNotificarAdmin("mailprueba@gmail.com");
		criterioComuna = new CriterioComuna(1);
		criterioComuna2 = new CriterioComuna(15);
		criterioPreSeleccionados = new CriterioPreSeleccionados(preSeleccionados);
	}

	@Test
	public void seAsignanLasAccionesALosUsuarioTest() {
		acciones.add(accionDesactivar);
		criterios.add(criterioTodosUsuarios);
		proceso = new AsignarAccionesUsuarios(repoUsuarios, acciones, criterios);
		proceso.asignarAcciones(repoUsuarios);
		Assert.assertTrue(usuario1.getListaDeAcciones().contains(accionDesactivar));

	}

	@Test
	public void seFiltranLosUsuariosCorrectamenteTest() {
		acciones.add(accionDesactivar);
		criterios.add(criterioComuna);
		proceso = new AsignarAccionesUsuarios(repoUsuarios, acciones, criterios);
		preSeleccionados = proceso.seleccionarUsuarios(repoUsuarios);
		Assert.assertTrue(preSeleccionados.contains(usuario2));
		Assert.assertTrue(preSeleccionados.contains(usuario3));
	}

	@Test
	public void unUsuarioCumpleLosCriteriosPrevistosTest() {
		acciones.add(accionDesactivar);
		criterios.add(criterioComuna);
		criterios.add(criterioTodosUsuarios);
		criterios.add(criterioPreSeleccionados);
		proceso = new AsignarAccionesUsuarios(repoUsuarios, acciones, criterios);
		Assert.assertFalse(proceso.cumpleCriterios(usuario1));
		Assert.assertTrue(proceso.cumpleCriterios(usuario3));
	}
	@Test
	public void ningunUsuarioCumpleLosCriteriosTest() {
		acciones.add(accionDesactivar);
		criterios.add(criterioComuna);
		criterios.add(criterioComuna2);
		proceso = new AsignarAccionesUsuarios(repoUsuarios, acciones, criterios);
		preSeleccionados = proceso.seleccionarUsuarios(repoUsuarios);
		Assert.assertFalse(proceso.cumpleCriterios(usuario1));
		Assert.assertFalse(proceso.cumpleCriterios(usuario3));
	}

}

/*
 * ArrayList<Usuario> seleccionarUsuarios(RepoUsuarios repoUsuario) { return
 * repoUsuario.getUsuarios().stream() .filter(unUsuario ->
 * listaDeCriterios.stream() .allMatch(unCriterio ->
 * unCriterio.esCumplidoPor(unUsuario)))
 * .collect(Collectors.toCollection(ArrayList::new)); }
 * 
 * public void asignarAcciones(RepoUsuarios repoUsuarios){ ArrayList<Usuario>
 * usuarios = seleccionarUsuarios(repoUsuarios); usuarios.forEach(usuario->
 * usuario.addAccion(listaDeAcciones)); }
 * 
 */
