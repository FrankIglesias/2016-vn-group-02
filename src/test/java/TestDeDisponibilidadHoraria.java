import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


public class TestDeDisponibilidadHoraria extends PoiMainTest {

	@Before
	public void init(){
		super.init();
		
	}
	@Test
	public void testDisponibilidadHorariaDeBanco() {
		GregorianCalendar fechaActual = new  GregorianCalendar(2014, 04, 13, 14, 25);
		
			assertTrue(banco.estaDisponible(fechaActual));
			
	}

	@Test
	public void testDisponibilidadHorariaServicioCGP() {
		GregorianCalendar fechaActual = new  GregorianCalendar(2014, 04, 13, 14, 25);
		
			assertTrue(d.estaDisponible(fechaActual));
			
	}
}
