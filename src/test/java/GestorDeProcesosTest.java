import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ActualizarLocalesComerciales.ActualizadorDeLC;
import Repositorio.ActualizadorDeRepositorioDePoi;
import Repositorio.GestorDeProcesos;
import Repositorio.RepoPOIs;

public class GestorDeProcesosTest {

	GestorDeProcesos gestor;
	ActualizadorDeLC actualizador;
	RepoPOIs repo;
	DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	int numero = 0;
	Date date;
	Date date1;

	@Before
	public void init() {
		gestor = new GestorDeProcesos();
		actualizador = new ActualizadorDeLC();
		repo = RepoPOIs.getInstance();
		repo.inicializarPuntosDeIntereses();
		numero = repo.size(); // hace que por cada test se instancie en 0
		repo.addLocal("LoDeMari", new ArrayList<String>());
	}

	@Test
	public void actualizacionProgramadaYConcretadaTest() {
		try {
			date = formatoFecha.parse("2016-06-30 01:59:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		gestor.setProceso(new ActualizadorDeRepositorioDePoi(), date);
		gestor.correrProcesos();
		Assert.assertTrue(repo.size() > 0);
	}

	@Test
	public void testVariosProcesosenDistintosHorarios() throws Exception {
		try {
			date = formatoFecha.parse("2016-06-30 01:59:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		gestor.setProceso(new ActualizadorDeRepositorioDePoi(), date);
		gestor.setProceso(new ActualizadorDeLC(), date);
		gestor.correrProcesos();
		Assert.assertEquals(repo.size(),4);
	}

	@Test
	public void testVariosProcesosenMismosHorarios() throws Exception {
		try {
			date = formatoFecha.parse("2016-06-30 23:06:00");
			date1 = formatoFecha.parse("2016-06-30 23:45:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		gestor.setProceso(new ActualizadorDeRepositorioDePoi(), date);
		gestor.setProceso(new ActualizadorDeLC(), date1);
		gestor.correrProcesos();
		Assert.assertEquals(repo.size(),4);
	}
}