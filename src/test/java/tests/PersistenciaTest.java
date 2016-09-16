package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import AsignarAccionesUsuario.AccionNotificarAdmin;
import DesignDreamTeamTime.Feriado;
import DesignDreamTeamTime.IntervaloHorario;
import Repositorios.Buscador;
import Repositorios.Busqueda;
import Repositorios.RepoDeBusquedas;
import Repositorios.RepoPOIs;
import Repositorios.Terminal;
import TypePois.Banco;
import TypePois.CGP;
import TypePois.Colectivo;
import TypePois.Local;

public class PersistenciaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	private RepoPOIs repositorioPOI;

	@Before
	public void setUp() {
		repositorioPOI = new RepoPOIs();
	}

	@Test
	public void contextUp() {
		assertNotNull(entityManager());
	}

	@Test
	public void contextUpWithTransaction() throws Exception {
		withTransaction(() -> {
		});
	}

	@Test
	public void alPedirleQueGuardeUnBancoPersiste() {
		Banco unBanco = GlobalTestVariables.crearUnBanco(null);

		repositorioPOI.persistirObjeto(unBanco);

		assertEquals(repositorioPOI.obtenerObjeto(unBanco.getId()), unBanco);
	}

	@Test
	public void persistoUnCGPyMeLoTraigoSanoYsalvo() {

		CGP unCGP = GlobalTestVariables.crearUnCGP(null);
		repositorioPOI.persistirObjeto(unCGP);
		assertEquals(repositorioPOI.obtenerObjeto(unCGP.getId()), unCGP);

	}

	@Test

	public void persistoUnColecYmeLoTraigoSanoYSalvo() {
		Colectivo unColec = GlobalTestVariables.crearUnColectivo();

		repositorioPOI.persistirObjeto(unColec);
		assertEquals(repositorioPOI.obtenerObjeto(unColec.getId()), unColec);
	}

	@Test

	public void persistoUnLocalYmeLoTraigoSanoYSalvo() {
		Local lodemari = GlobalTestVariables.crearUnLocal(null);

		repositorioPOI.persistirObjeto(lodemari);
		assertEquals(repositorioPOI.obtenerObjeto(lodemari.getId()), lodemari);
	}

	@Test
	public void alObtenerUnPOICoincideConElPersistido() {

		Banco unBanco = GlobalTestVariables.crearUnBanco(null);
		repositorioPOI.persistirObjeto(unBanco);
		Banco otroBanco = (Banco) repositorioPOI.obtenerObjeto(unBanco.getId());
		Assert.assertEquals(otroBanco.getNombre(), "Banco Rio");
	}

	@Test
	public void obtengoFeriadoDespuesDeSerPersistidoConUnPoi() {
		LocalTime hora1 = LocalTime.of(10, 00);
		LocalTime hora2 = LocalTime.of(15, 00);
		IntervaloHorario intervalo1 = new IntervaloHorario(hora1, hora2);
		Feriado feriado = new Feriado(9, 15, intervalo1);
		Colectivo colectivo = new Colectivo();
		colectivo.setNombre("colectivo1");
		colectivo.addFeriado(feriado);
		repositorioPOI.persistirObjeto(colectivo);

		Colectivo unColectivo = (Colectivo) repositorioPOI.obtenerObjeto(colectivo.getId());
		Assert.assertTrue(unColectivo.getNombre() == "colectivo1");
		Assert.assertTrue(unColectivo.getUnFeriado(LocalDate.of(2016, 9, 15)) == feriado);

	}

	@Test
	public void persistoUnBancoYMeTraigoSuLatitud() {
		Banco unBanco = GlobalTestVariables.crearUnBanco(null);
		repositorioPOI.persistirObjeto(unBanco);
		Banco otroBanco = (Banco) repositorioPOI.obtenerObjeto(unBanco.getId());
		Assert.assertTrue(otroBanco.getPoint().getLatitud() == -34.5735632);
		Assert.assertTrue(otroBanco.getNombre() == "Banco Rio");
	}

	@Test
	public void persistoCGPYObtengoListaDeServicios() {
		LocalTime hora1 = LocalTime.of(10, 00);
		LocalTime hora2 = LocalTime.of(15, 00);
		IntervaloHorario intervalo1 = new IntervaloHorario(hora1, hora2);
		Feriado feriado = new Feriado(9, 15, intervalo1);

		LocalTime hora3 = LocalTime.of(14, 00);
		LocalTime hora4 = LocalTime.of(12, 00);
		IntervaloHorario intervalo2 = new IntervaloHorario(hora3, hora4);
		Feriado feriado2 = new Feriado(10, 20, intervalo2);

		ArrayList<Feriado> feriados = new ArrayList<Feriado>();
		feriados.add(feriado);
		feriados.add(feriado2);
		CGP unCGP = GlobalTestVariables.crearUnCGP(feriados);
		repositorioPOI.persistirObjeto(unCGP);

		CGP otroCGP = (CGP) repositorioPOI.obtenerObjeto(1);

		Assert.assertTrue(otroCGP.getServicios() == unCGP.getServicios());

	}

	@Test
	public void persistoUnBancoYMeTraigoSusPalabrasClave() {
		Banco unBanquito = GlobalTestVariables.crearUnBanco(GlobalTestVariables.crearFeriadoNoAbierto());
		repositorioPOI.persistirObjeto(unBanquito);
		Banco otroBanquito = (Banco) repositorioPOI.obtenerObjeto(unBanquito.getId());
		Assert.assertEquals(otroBanquito.getPalabrasClave(), unBanquito.getPalabrasClave());

	}

	@Test
	public void persistirUnaBusqueda() {
		Buscador buscador2 = new Buscador();
		List<TypePois.POI> lista2 = new ArrayList<TypePois.POI>();
		Terminal terminal = new Terminal("TerminalPepito");
		lista2.add(GlobalTestVariables.crearUnBanco(null));
		buscador2.setTiempoMaximoDeBusqueda(0.01);
		Buscador.setPuntosDeIntereses(lista2);
		buscador2.buscarSegunPalabraClave("banelco", terminal);
		RepoDeBusquedas repo = RepoDeBusquedas.getInstance();
		Busqueda busqueda1 = repo.getBusquedas().get(0);
		repo.persistirObjeto(repo.getBusquedas().get(0));
		Assert.assertEquals(repo.obtenerObjeto(busqueda1.getId()), busqueda1);
	}
	
	@Test
	public void persistirUnaAccionDeBusqueda() {
		AccionNotificarAdmin unaAccion = new AccionNotificarAdmin("hola");
		
		RepoDeBusquedas repo = RepoDeBusquedas.getInstance();
		
		repo.persistirObjeto(unaAccion);
		
		System.out.print(repo.obtenerObjeto(unaAccion.getId()));
		
		Assert.assertEquals(repo.obtenerObjeto(unaAccion.getId()), unaAccion);
		
	}
}
