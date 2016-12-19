package tests;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Repositorios.Buscador;
import Repositorios.RepoDeBusquedas;
import Repositorios.RepoTerminales;
import Repositorios.Terminal;


public class BaseDeDatosTest {
	
	Buscador buscador;
	Terminal terminal;
	RepoDeBusquedas baseDeDatos;
	RepoTerminales repoTerminales;
	
	@Before
	public void init() {
		buscador = new Buscador();
		baseDeDatos = RepoDeBusquedas.getInstance();
		baseDeDatos.inicializarBaseDeDatos();
		repoTerminales = RepoTerminales.getInstance();
		repoTerminales.inicializarRepoTerminales();
		terminal = new Terminal("mari");
		buscador.buscarPoisMongo("prueba", terminal);
		
	}
	
	@Test
	public void cantidadDeBusquedasPorFechaTest() {
		Assert.assertEquals(baseDeDatos.cantidadDeBusquedasPorFecha(LocalDate.now()), 1);
	}
	
	@Test
	public void cantidadBusquedasPorFechasDelReporteTest() {
		Assert.assertEquals(baseDeDatos.getReportePorFecha().size(), 1);
	}
	
	@Test
	public void cantidadDeResultadosParcialesPorTerminalTest() {
		Assert.assertEquals(terminal.getReporteParcialPorTerminal().size(), 1);
	}
	
	@Test
	public void cantidadDeBusquedasTotalesTest() {
		Assert.assertEquals(repoTerminales.getReporteBusquedasTotales().size(), 1);
	}
	
	
}
