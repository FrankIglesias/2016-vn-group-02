package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import AsignarAccionesUsuario.AccionNotificarAdmin;
import DesignDreamTeamLocation.Domicilio;
import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamLocation.Localidad;
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
	LocalTime hora1;
	LocalTime hora2;
	IntervaloHorario intervalo1;
	Feriado feriado;
	LocalTime hora3;
	LocalTime hora4;
	IntervaloHorario intervalo2;
	Feriado feriado2;
	ArrayList<Feriado> feriados;
	

	@Before
	public void setUp() {
		repositorioPOI = new RepoPOIs();
		hora1 = LocalTime.of(10, 00);
		hora2 = LocalTime.of(15, 00);
		intervalo1 = new IntervaloHorario(hora1, hora2);
		feriado = new Feriado(9, 15, intervalo1);

		hora3 = LocalTime.of(14, 00);
		hora4 = LocalTime.of(12, 00);
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
	public void persistoDomicilio()
	{
		Domicilio unDomicilio = new Domicilio("basavilbaso", "guido y eva peron", "1420", "0", "1", "1824", 1);
		//	repositorioPOI.persistirObjeto(unBanco);
			EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
			EntityTransaction transaccion = entityManager.getTransaction();
			transaccion.rollback();
			transaccion.begin();
			entityManager.persist(unDomicilio);
			transaccion.commit();
			Domicilio unDom = entityManager.find(Domicilio.class, unDomicilio.getID());
			assertEquals("basavilbaso", unDom.getCallePrincipal());

			Assert.assertTrue("basavilbaso" == unDomicilio.getCallePrincipal());
	}	
	
	@Test
	public void persistoUnCGPyMeLoTraigoSanoYsalvo() {

		CGP unCGP = GlobalTestVariables.crearUnCGP(null);
		repositorioPOI.persist(unCGP);
		assertEquals(repositorioPOI.obtenerObjeto(unCGP.getId()), unCGP);

	}
	@Test
	public void persistirIntervaloHorario()
	{
		IntervaloHorario unIntervalo = new IntervaloHorario(LocalTime.now(), LocalTime.now());
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
	//	repositorioPOI.persistirObjeto(unBanco);
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaccion = entityManager.getTransaction();
		transaccion.rollback();
		transaccion.begin();
		entityManager.persist(unBanco);
		transaccion.commit();
		Banco otroBanco = entityManager.find(Banco.class, unBanco.getId());

		Assert.assertTrue(unBanco == otroBanco);
	}

	

	@Test

	public void persistoUnColecYmeLoTraigoSanoYSalvo() {
		Colectivo unColec = GlobalTestVariables.crearUnColectivo();

		repositorioPOI.persist(unColec);
		assertEquals(repositorioPOI.obtenerObjeto(unColec.getId()), unColec);
	}

	@Test

	public void persistoUnLocalYmeLoTraigoSanoYSalvo() {
		Local lodemari = GlobalTestVariables.crearUnLocal(null);
		repositorioPOI.persist(lodemari);
		//repositorioPOI.persistirObjeto(lodemari);
		assertEquals(repositorioPOI.obtenerObjeto(lodemari.getId()), lodemari);
	}

	@Test
	public void alObtenerUnPOICoincideConElPersistido() {

		Banco unBanco = GlobalTestVariables.crearUnBanco(null);
		//repositorioPOI.persistirObjeto(unBanco);
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaccion = entityManager.getTransaction();
		transaccion.rollback();
		transaccion.begin();
		entityManager.persist(unBanco);
		transaccion.commit();
		Banco otroBanco = entityManager.find(Banco.class, unBanco.getId());
		Assert.assertEquals(otroBanco.getNombre(), "Banco Rio");
	}

	@Test
	public void persistoUnFeriado() {
		LocalTime hora1 = LocalTime.of(10, 00);
		LocalTime hora2 = LocalTime.of(15, 00);
		IntervaloHorario intervalo1 = new IntervaloHorario(hora1, hora2);
		Feriado feriado = new Feriado(9, 15, intervalo1);
		//Colectivo colectivo = GlobalTestVariables.crearUnColectivo();
		//colectivo.setNombre("colectivo1");
		//colectivo.addFeriado(feriado);
		//repositorioPOI.persistirObjeto(colectivo);
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaccion = entityManager.getTransaction();
		transaccion.rollback();
		transaccion.begin();
		entityManager.persist(feriado);
		transaccion.commit();

		Feriado unFeriado = entityManager.find(Feriado.class, feriado.getId());
		Assert.assertTrue(unFeriado.getMes() == feriado.getMes());

	}

	@Test
	public void persistoUnBancoYMeTraigoSuLatitud() {
		Banco unBanco = GlobalTestVariables.crearUnBanco(null);
		repositorioPOI.persist(unBanco);
		Banco otroBanco = (Banco) repositorioPOI.obtenerObjeto(unBanco.getId());
		Assert.assertTrue(otroBanco.getPoint().getLatitud() == -34.5735632);
		Assert.assertTrue(otroBanco.getNombre() == "Banco Rio");
	}
	@Test 
	public void persistoUnLocalYObtengoSuCiudad()
	{
		Local unLocal = GlobalTestVariables.crearUnLocal(feriados);
		
		//repositorioPOI.persistirObjeto(unLocal);
		repositorioPOI.persist(unLocal);
		Local otroLocal = (Local) repositorioPOI.obtenerObjeto(unLocal.getId());
		String ciudadLocal = otroLocal.getPoint().getLocalidad().getCiudad();
		Assert.assertTrue(unLocal.getPoint().getLocalidad().getCiudad() == ciudadLocal);
		
		
		
	}

	/*@Test
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
		AccionNotificarAdmin otraAccion = (AccionNotificarAdmin) repo.obtenerObjetoAccion(1);
		Assert.assertTrue(otraAccion == unaAccion);
	}*/
}
