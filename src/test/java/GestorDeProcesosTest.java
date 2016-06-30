import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ActualizarLocalesComerciales.ActualizadorDeLC;
import Repositorio.GestorDeProcesos;
import Repositorio.RepoPOIs;

public class GestorDeProcesosTest {

	GestorDeProcesos gestor;
	ActualizadorDeLC actualizador;
	RepoPOIs repo;
	DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date;

	@Before
	public void init() {
		gestor = new GestorDeProcesos();
		actualizador = new ActualizadorDeLC();
		repo = RepoPOIs.getInstance();
		repo.inicializarPuntosDeIntereses();

		try {
			date = formatoFecha.parse("2016-06-30 13:43:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void actualizacionProgramadaYConcretadaTest() {
		int numero = repo.size();
		gestor.setProceso(actualizador, date);
		System.out.println(numero);
		System.out.println(repo.size());
		Assert.assertTrue(repo.size()>numero);
		
		
	}

}
