

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;


public class TestDeDisponibilidadHoraria extends PoiMainTest {

	@Before
	public void init(){
		super.init();
		 fechaActual = new  GregorianCalendar(2014, 04, 13, 14, 25);
	}
	@Test
	public void testDisponibilidadHorariaDeBanco() {
		
		
			assertTrue(banco.estaDisponible(fechaActual));
			
	}

	@Test
	public void testDisponibilidadHorariaServicioCGP() {
		
		
			assertTrue(ventaDeVOS.estasDisponibleEn(fechaActual));
			
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
