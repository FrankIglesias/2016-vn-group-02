import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;


public class TestDeDisponibilidadHoraria extends PoiMainTest {

	@Before
	public void init(){
		super.init();
		fecha1 = LocalDate.of(2016,04,15);
		hora1 = LocalTime.of(10,40);
		fechaAbierto = LocalDateTime.of(fecha1, hora1) ;
		fecha2= LocalDate.of(2016,04,15);
		hora2= LocalTime.of(03,00);
		fechaCerrado = LocalDateTime.of(fecha2, hora2);
		
	}
	@Test
	public void testDisponibilidadHorariaDeBanco() {
		
		
			assertTrue(banco.estaDisponible(fechaAbierto));
			assertFalse(banco.estaDisponible(fechaCerrado));
	}

	@Test
	public void testDisponibilidadHorariaServicioCGP() {
		
		
			assertTrue(ventaDeVOS.estaDisponible(fechaAbierto));
			assertFalse(ventaDeVOS.estaDisponible(fechaCerrado));
	}
	@Test
	public void testDisponibilidadHorariaCGP() {
		
		
			assertTrue(centroDeCGP.estaDisponible(fechaAbierto)); 
			assertFalse(centroDeCGP.estaDisponible(fechaCerrado));
	}
	@Test
	public void testDisponibilidadHorariaColectivo() {
		
		
			assertTrue(colectivo.estaDisponible(fechaAbierto)); 
			assertTrue(colectivo.estaDisponible(fechaCerrado));
			// El colectivo no deja de estar disponible en ningun horario
	}
	@Test
	public void testDisponibilidadHorariaLocal() {
		
		assertTrue(unLocal.estaDisponible(fechaAbierto)); 
		assertFalse(unLocal.estaDisponible(fechaCerrado));
	}
}
