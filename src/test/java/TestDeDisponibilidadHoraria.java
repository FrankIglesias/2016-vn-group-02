

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;


public class TestDeDisponibilidadHoraria extends PoiMainTest {

	@Before
	public void init(){
		super.init();
		fecha = LocalDate.of(2016,04,15);
		hora = LocalTime.of(10,40);
		fechaActual = LocalDateTime.of(fecha, hora) ;
	}
	@Test
	public void testDisponibilidadHorariaDeBanco() {
		
		
			assertTrue(banco.estaDisponible(fechaActual));
			
	}

	@Test
	public void testDisponibilidadHorariaServicioCGP() {
		
		
			assertTrue(ventaDeVOS.estaDisponible(fechaActual));
			
	}
	@Test
	public void testDisponibilidadHorariaCGP() {
		
		
			assertTrue(centroDeCGP.estaDisponible(fechaActual)); 
			
	}
	@Test
	public void testDisponibilidadHorariaColectivo() {
		
		
			assertTrue(colectivo.estaDisponible(fechaActual)); 
			
	}
	@Test
	public void testDisponibilidadHorariaLocal() {
		
		assertTrue(unLocal.estaDisponible(fechaActual)); 
			
	}
}
