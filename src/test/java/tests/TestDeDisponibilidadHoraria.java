package tests;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import TypePois.Banco;
import TypePois.CGP;
import TypePois.Colectivo;
import TypePois.Local;
import TypePois.Servicio;

public class TestDeDisponibilidadHoraria {
	private LocalDateTime fechaAbierto;
	private LocalDateTime fechaCerrado;
	private LocalDate fecha1;
	private LocalTime hora1;
	private LocalDate fecha2;
	private LocalTime hora2;
	private Banco banco;
	private Servicio ventaDeVOS;
	private CGP centroDeCGP;
	private Colectivo colectivo;
	private Local unLocal;

	@Before
	public void init() {
		fecha1 = LocalDate.of(2016, 04, 15);
		hora1 = LocalTime.of(10, 40);
		fechaAbierto = LocalDateTime.of(fecha1, hora1);
		fecha2 = LocalDate.of(2016, 04, 15);
		hora2 = LocalTime.of(03, 00);
		fechaCerrado = LocalDateTime.of(fecha2, hora2);
		centroDeCGP = GlobalTestVariables.crearUnCGP(GlobalTestVariables.crearFeriadoVacio());
		banco = GlobalTestVariables.crearUnBanco(GlobalTestVariables.crearFeriadoVacio());
		colectivo = GlobalTestVariables.crearUnColectivo();
		unLocal = GlobalTestVariables.crearUnLocal(GlobalTestVariables.crearFeriadoVacio());
		ventaDeVOS = new Servicio("Tarjeta vos", GlobalTestVariables.crearUnHorarioDeLunesAViernes(LocalTime.of(8, 00), LocalTime.of(21, 00)),GlobalTestVariables.crearFeriadoVacio());
		

	}

	@Test
	public void testBancoAbierto() {

		assertTrue(banco.estaDisponible(fechaAbierto));
	}

	@Test
	public void testBancoCerrado() {

		assertFalse(banco.estaDisponible(fechaCerrado));
	}

	@Test
	public void testServicioCGPDisponible() {

		assertTrue(ventaDeVOS.estaDisponible(fechaAbierto));
	}

	@Test
	public void testServicioCGPNoDisponible() {

		assertFalse(ventaDeVOS.estaDisponible(fechaCerrado));
	}

	@Test
	public void testCGPAbierto() {

		assertTrue(centroDeCGP.estaDisponible(fechaAbierto));
	}

	@Test
	public void testCGPCerrado() {

		assertFalse(centroDeCGP.estaDisponible(fechaCerrado));
	}

	@Test
	public void testColectivoDisponibleSiempre1() {

		assertTrue(colectivo.estaDisponible(fechaAbierto));

	}

	@Test
	public void testColectivoDisponibleSiempre2() {

		assertTrue(colectivo.estaDisponible(fechaCerrado));
	}

	// El colectivo no deja de estar disponible en ningun horario
	@Test
	public void testLocalAbierto() {

		assertTrue(unLocal.estaDisponible(fechaAbierto));

	}

	@Test
	public void testLocalCerrado() {
		assertFalse(unLocal.estaDisponible(fechaCerrado));
	}

}
