package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import DesignDreamTeamLocation.Localidad;
import DesignDreamTeamTime.Feriado;
import DesignDreamTeamTime.GestorIntervalos;
import DesignDreamTeamTime.IntervaloHorario;
import Repositorios.RepoPOIs;
import TypePois.Banco;
import TypePois.CGP;
import TypePois.Colectivo;
import TypePois.Local;

public class PersistenciaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	private RepoPOIs repositorioPOI;
	LocalDateTime hora1;
	LocalDateTime hora2;
	IntervaloHorario intervalo1;
	Feriado feriado;
	LocalDateTime hora3;
	LocalDateTime hora4;
	IntervaloHorario intervalo2;
	Feriado feriado2;
	ArrayList<Feriado> feriados;
	ArrayList<IntervaloHorario> lista;
	
	@Before
	public void setUp() {
		repositorioPOI = new RepoPOIs();
		hora1 = LocalDateTime.now().withHour(10).withMinute(00);
		hora2 =  LocalDateTime.now().withHour(15).withMinute(00);
		intervalo1 = new IntervaloHorario(hora1, hora2);
		feriado = new Feriado(9, 15, intervalo1);

		hora3 = LocalDateTime.now().withHour(14).withMinute(00);
		hora4 = LocalDateTime.now().withHour(12).withMinute(00);
		intervalo2 = new IntervaloHorario(hora3, hora4);
		feriado2 = new Feriado(10, 20, intervalo2);

		feriados = new ArrayList<Feriado>();
		feriados.add(feriado);
		feriados.add(feriado2);
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
	public void persistoLocalidad() {
		Localidad unaLocalidad = new Localidad("Lanus", "Buenos Aires", "Argentina");
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaccion = entityManager.getTransaction();
		transaccion.rollback();
		transaccion.begin();
		entityManager.persist(unaLocalidad);
		transaccion.commit();
		Localidad otraLocalidad = entityManager.find(Localidad.class, unaLocalidad.getID());
		assertEquals("Lanus", otraLocalidad.getCiudad());
	}

	@Test
	public void persistoUnCGPyMeLoTraigoSanoYsalvo() {

		CGP unCGP = GlobalTestVariables.crearUnCGP(null);
		repositorioPOI.persistirEnHibernate(unCGP);
		assertEquals(repositorioPOI.obtenerDeHibernate(unCGP.getId()), unCGP);

	}

	@Test
	public void persistirIntervaloHorario() {
		IntervaloHorario unIntervalo = new IntervaloHorario(LocalDateTime.now(), LocalDateTime.now());
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaccion = entityManager.getTransaction();
		transaccion.rollback();
		transaccion.begin();
		entityManager.persist(unIntervalo);
		transaccion.commit();

	}

	@Test
	public void alPedirleQueGuardeUnBancoPersiste() {
		Banco unBanco = GlobalTestVariables.crearUnBanco(null);
		// repositorioPOI.persistirObjeto(unBanco);
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaccion = entityManager.getTransaction();

		entityManager.persist(unBanco);

		Banco otroBanco = entityManager.find(Banco.class, unBanco.getId());

		Assert.assertTrue(unBanco == otroBanco);
	}

	@Test

	public void persistoUnColecYmeLoTraigoSanoYSalvo() {
		Colectivo unColec = GlobalTestVariables.crearUnColectivo();

		repositorioPOI.persistirEnHibernate(unColec);
		assertEquals(repositorioPOI.obtenerDeHibernate(unColec.getId()), unColec);
	}

	@Test

	public void persistoUnLocalYmeLoTraigoSanoYSalvo() {
		Local lodemari = GlobalTestVariables.crearUnLocal(GlobalTestVariables.crearFeriadoAbierto());
		repositorioPOI.persistirEnHibernate(lodemari);
		assertEquals(repositorioPOI.obtenerDeHibernate(lodemari.getId()), lodemari);
	}

	@Test
	public void alObtenerUnPOICoincideConElPersistido() {

		Banco unBanco = GlobalTestVariables.crearUnBanco(null);
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		RepoPOIs.getInstance().persistirEnHibernate(unBanco);
		Banco otroBanco = entityManager.find(Banco.class, unBanco.getId());
		Assert.assertEquals(otroBanco.getNombre(), "Banco Rio");
	}

	@Test
	public void persistoUnFeriado() {
		LocalDateTime hora1 = LocalDateTime.now().withHour(10).withMinute(00);
		LocalDateTime hora2 = LocalDateTime.now().withHour(15).withMinute(00);
		IntervaloHorario intervalo1 = new IntervaloHorario(hora1, hora2);
		Feriado feriado = new Feriado(9, 15, intervalo1);
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaccion = entityManager.getTransaction();

		entityManager.persist(feriado);

		Feriado unFeriado = entityManager.find(Feriado.class, feriado.getId());
		Assert.assertTrue(unFeriado.getMes() == feriado.getMes());

	}
	
	@Test
	public void persistoUnDiaYHora() {
	
		LocalDateTime hora1 = LocalDateTime.now().withHour(10).withMinute(00);
		LocalDateTime hora2 = LocalDateTime.now().withHour(15).withMinute(00);
		IntervaloHorario intervalo = new IntervaloHorario(hora1, hora2);
		lista = new ArrayList<IntervaloHorario>();
		lista.add(intervalo);
		GestorIntervalos gestor = new GestorIntervalos(lista);
		Map<DayOfWeek, GestorIntervalos> agenda = new HashMap<DayOfWeek, GestorIntervalos>();
		agenda.put(DayOfWeek.FRIDAY, gestor);
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		entityManager.persist(agenda);
	}

	@Test
	public void persistoUnBancoYMeTraigoSuLatitud() {
		Banco unBanco = GlobalTestVariables.crearUnBanco(null);
		repositorioPOI.persistirEnHibernate(unBanco);
		Banco otroBanco = (Banco) repositorioPOI.obtenerDeHibernate(unBanco.getId());
		Assert.assertTrue(otroBanco.getPoint().getLatitud() == -34.5735632);
		Assert.assertTrue(otroBanco.getNombre() == "Banco Rio");
	}

	@Test
	public void persistoUnLocalYObtengoSuCiudad() {
		Local unLocal = GlobalTestVariables.crearUnLocal(feriados);

		// repositorioPOI.persistirObjeto(unLocal);
		repositorioPOI.persistirEnHibernate(unLocal);
		Local otroLocal = (Local) repositorioPOI.obtenerDeHibernate(unLocal.getId());
		String ciudadLocal = otroLocal.getPoint().getLocalidad().getCiudad();
		Assert.assertTrue(unLocal.getPoint().getLocalidad().getCiudad() == ciudadLocal);

	}

	/*
	 * @Test public void persistirUnaBusqueda() { Buscador buscador2 = new
	 * Buscador(); List<TypePois.POI> lista2 = new ArrayList<TypePois.POI>();
	 * Terminal terminal = new Terminal("TerminalPepito");
	 * lista2.add(GlobalTestVariables.crearUnBanco(null));
	 * buscador2.setTiempoMaximoDeBusqueda(0.01);
	 * Buscador.setPuntosDeIntereses(lista2);
	 * buscador2.buscarSegunPalabraClave("banelco", terminal); RepoDeBusquedas
	 * repo = RepoDeBusquedas.getInstance(); Busqueda busqueda1 =
	 * repo.getBusquedas().get(0);
	 * repo.persistirObjeto(repo.getBusquedas().get(0));
	 * Assert.assertEquals(repo.obtenerObjeto(busqueda1.getId()), busqueda1); }
	 * 
	 * @Test public void persistirUnaAccionDeBusqueda() { AccionNotificarAdmin
	 * unaAccion = new AccionNotificarAdmin("hola"); RepoDeBusquedas repo =
	 * RepoDeBusquedas.getInstance(); repo.persistirObjeto(unaAccion);
	 * AccionNotificarAdmin otraAccion = (AccionNotificarAdmin)
	 * repo.obtenerObjetoAccion(1); Assert.assertTrue(otraAccion == unaAccion);
	 * }
	 */
}
