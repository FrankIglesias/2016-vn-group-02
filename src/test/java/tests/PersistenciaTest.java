package tests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import DesignDreamTeamLocation.Domicilio;
import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamLocation.Localidad;
import DesignDreamTeamTime.Feriado;
import DesignDreamTeamTime.IntervaloHorario;
import Repositorios.RepoPOIs;
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
		unBanco.setId(1L);

		repositorioPOI.persistirObjeto(unBanco);

		assertEquals(repositorioPOI.obtenerObjeto(unBanco.getId()), unBanco);
	}

	@Test
	public void persistoUnCGPyMeLoTraigoSanoYsalvo() {

		CGP unCGP = GlobalTestVariables.crearUnCGP(null);
		unCGP.setId(2L);

		repositorioPOI.persistirObjeto(unCGP);
		assertEquals(repositorioPOI.obtenerObjeto(unCGP.getId()), unCGP);

	}

	@Test

	public void persistoUnColecYmeLoTraigoSanoYSalvo() {
		Colectivo unColec = GlobalTestVariables.crearUnColectivo();
		unColec.setId(2L);

		repositorioPOI.persistirObjeto(unColec);
		assertEquals(repositorioPOI.obtenerObjeto(unColec.getId()), unColec);
	}

	@Test

	public void persistoUnLocalYmeLoTraigoSanoYSalvo() {
		Local lodemari = GlobalTestVariables.crearUnLocal(null);
		lodemari.setId(2L);

		repositorioPOI.persistirObjeto(lodemari);
		assertEquals(repositorioPOI.obtenerObjeto(lodemari.getId()), lodemari);
	}

	@Test
	public void alObtenerUnPOICoincideConElPersistido() {

		Banco unBanco = GlobalTestVariables.crearUnBanco(null);
		unBanco.setId(1L);
		repositorioPOI.persistirObjeto(unBanco);
		Banco otroBanco = (Banco) repositorioPOI.obtenerObjeto(1L);
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
		colectivo.setId(2L);
		repositorioPOI.persistirObjeto(colectivo);

		Colectivo unColectivo = (Colectivo) repositorioPOI.obtenerObjeto(2L);
		Assert.assertTrue(unColectivo.getNombre() == "colectivo1");
		//Assert.assertTrue(unColectivo.getUnFeriado(LocalDate.now()) == feriado); 

	}

	@Test
	public void persistoUnBancoYMeTraigoSuLatitud() {
		Banco unBanco = GlobalTestVariables.crearUnBanco(null);
		unBanco.setId(1L);

		repositorioPOI.persistirObjeto(unBanco);

		Banco otroBanco = (Banco) repositorioPOI.obtenerObjeto(1L);

		Assert.assertTrue(otroBanco.getPoint().getLatitud() == -34.5735632);
		Assert.assertTrue(otroBanco.getNombre() == "Banco Rio");
	}

	@Test

	public void persistoUnBancoYMeTraigoSusPalabrasClave() {
		Banco unBanquito = GlobalTestVariables.crearUnBanco(GlobalTestVariables.crearFeriadoNoAbierto());
		unBanquito.setId(2L);

		repositorioPOI.persistirObjeto(unBanquito);

		Banco otroBanquito = (Banco) repositorioPOI.obtenerObjeto(2L);

		Assert.assertEquals(otroBanquito.getPalabrasClave(), unBanquito.getPalabrasClave());

	}

}
