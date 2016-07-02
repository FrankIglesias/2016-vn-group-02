import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ActualizarLocalesComerciales.ActualizadorDeLC;
import DesignDreamTeamProcesses.GestorDeProcesos;
import Repositorio.ActualizadorDeRepositorioDePoi;
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
		actualizador = new ActualizadorDeLC(null, date);
		repo = RepoPOIs.getInstance();
		repo.inicializarPuntosDeIntereses();
		numero = repo.size(); // hace que por cada test se instancie en 0
		repo.addLocal("LoDeMari", new ArrayList<String>());
	}

	@Test
	public void actualizacionProgramadaYConcretadaTest() {
		 
		date = gestor.setFecha("2016-06-30 01:58:00");
		
		gestor.setProceso(new ActualizadorDeRepositorioDePoi(null, date));
		gestor.correrProcesos();
		Assert.assertTrue(repo.size() > 0);
	}

	@Test
	public void testVariosProcesosenDistintosHorarios() throws Exception {
		
		date = gestor.setFecha("2016-06-30 01:58:00");
		
		gestor.setProceso(new ActualizadorDeRepositorioDePoi(null, date));
		gestor.setProceso(new ActualizadorDeLC(null, date));
		gestor.correrProcesos();
		Assert.assertEquals(repo.size(),7);
	}

	@Test
	public void testVariosProcesosenMismosHorarios() {
		
		date = gestor.setFecha("2016-06-30 15:06:00");
		date1 = gestor.setFecha("2016-06-30 15:45:00");
		gestor.setProceso(new ActualizadorDeRepositorioDePoi(null, date));
		gestor.setProceso(new ActualizadorDeLC(null, date));
		gestor.correrProcesos();
		Assert.assertEquals(repo.size(),1);
	}
}