import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

public class TestDeDisponibilidadHoraria extends PoiMainTest {

	@Before
	public void init() {
		super.init();
		fecha1 = LocalDate.of(2016, 04, 15);
		hora1 = LocalTime.of(10, 40);
		fechaAbierto = LocalDateTime.of(fecha1, hora1);
		fecha2 = LocalDate.of(2016, 04, 15);
		hora2 = LocalTime.of(03, 00);
		fechaCerrado = LocalDateTime.of(fecha2, hora2);

	}

	@Test
	public void testBancoAbierto() {

		assertTrue(banco.estaDisponible(fechaAbierto));
	}

	@Test
	public void testBancoCerrado() {

		assertFalse(banco.estaDisponible(fechaCerrado));
	}

	/*
	 * @Test public void testServicioCGPDisponible() {
	 * 
	 * assertTrue(ventaDeVOS.estaDisponible(fechaAbierto)); }
	 * 
	 * @Test public void testServicioCGPNoDisponible() {
	 * 
	 * assertFalse(ventaDeVOS.estaDisponible(fechaCerrado)); }
	 * 
	 * @Test public void testCGPAbierto() {
	 * 
	 * assertTrue(centroDeCGP.estaDisponible(fechaAbierto));
	 * 
	 * }
	 * 
	 * @Test public void testCGPCerrado() {
	 * 
	 * assertFalse(centroDeCGP.estaDisponible(fechaCerrado));
	 * 
	 * }
	 */

	@Test
	public void testColectivoDisponibleSiempre() {

		assertTrue(colectivo.estaDisponible(fechaAbierto)
				&& colectivo.estaDisponible(fechaCerrado));
		// El colectivo no deja de estar disponible en ningun horario
	}

	@Test
	public void testLocalAbierto() {

		assertTrue(unLocal.estaDisponible(fechaAbierto));

	}

	@Test
	public void testLocalCerrado() {
		assertFalse(unLocal.estaDisponible(fechaCerrado));
	}

}
