import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class BaseDeDatosTest {
	
	Buscador buscador;
	Terminal terminal;
	BaseDeDatos baseDeDatos;
	
	@Before
	public void init() {
		buscador = new Buscador();
		baseDeDatos = BaseDeDatos.getInstance();
		baseDeDatos.inicializarBaseDeDatos();
		terminal = new Terminal("mari");
		buscador.buscarSegunPalabraClave("prueba", terminal);
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
		Assert.assertEquals(baseDeDatos.getReporteBusquedasTotales().size(), 1);
	}
	
	
}
