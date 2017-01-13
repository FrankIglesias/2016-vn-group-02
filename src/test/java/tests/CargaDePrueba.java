package tests;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import AsignarAccionesUsuario.AccionNotificarAdmin;
import AsignarAccionesUsuario.AsignarAccionesUsuarios;
import AsignarAccionesUsuario.CriterioTodosUsuarios;
import Controllers.ControllerRepoTerminales;
import Repositorios.RepoPOIs;
import Repositorios.RepoTerminales;
import Repositorios.Terminal;
import TypePois.Banco;
import TypePois.CGP;
import TypePois.Colectivo;
import TypePois.POI;

public class CargaDePrueba {

	RepoPOIs repoPois;
	List<POI> poisAPersistir;
	ControllerRepoTerminales repoTerminales;
	AsignarAccionesUsuarios lapoli;
	RepoTerminales repoPosta;

	@Before
	public void init() {
		repoPois = RepoPOIs.getInstance();
		repoTerminales = ControllerRepoTerminales.getInstance();
		repoPosta = RepoTerminales.getInstance();
		poisAPersistir = new ArrayList<POI>();
		Banco unBanco = GlobalTestVariables.crearUnBanco(GlobalTestVariables.crearFeriadoAbierto());
		unBanco.setUltimaFechaBusqueda(LocalDateTime.now().minusDays(10));

		Colectivo unColec = GlobalTestVariables.crearUnColectivo();
		unColec.setUltimaFechaBusqueda(LocalDateTime.now().minusDays(10));

		CGP unCGP = GlobalTestVariables.crearUnCGP(GlobalTestVariables.crearFeriadoAbierto());
		unCGP.setUltimaFechaBusqueda(LocalDateTime.now().minusDays(10));

		poisAPersistir.add(unBanco);
		poisAPersistir.add(unColec);
		poisAPersistir.add(unCGP);
		poisAPersistir.add(GlobalTestVariables.crearOtroCGP(GlobalTestVariables.crearFeriadoAbierto()));
		poisAPersistir.add(GlobalTestVariables.crearUnLocal(GlobalTestVariables.crearFeriadoAbierto()));

		lapoli = AsignarAccionesUsuarios.getInstance();
		CriterioTodosUsuarios todos = new CriterioTodosUsuarios();
		lapoli.setCriterio(todos);
	}

	//@Test
	public void cargarTodoEnMongo() {
		try {
			poisAPersistir.stream().forEach(unPoi -> repoPois.persistirEnHibernate(unPoi));
		} catch (Exception e) {
			e.printStackTrace();
		}

		poisAPersistir.stream().forEach(unPoi -> repoPois.persistirEnMongo(unPoi));
		repoPois.sincronizarBDs();
		cargarLaTerminalUsuario();
		cargarUnaAccionAUnaTerminal();
	}
	@Test
	public void cargarLaTerminalUsuario() {
		Terminal unaTermi = new Terminal("Usuario");
		unaTermi.addAccion(new AccionNotificarAdmin("Esta terdando mucho la busqueda viejo"));
		RepoTerminales.getInstance().add(unaTermi);
		
	}

	public void cargarUnaAccionAUnaTerminal() {
		AccionNotificarAdmin unaAccion = new AccionNotificarAdmin("mensajito");
		repoTerminales.setearAccionParaUnaTerminal("usuario", unaAccion);
	}

}
