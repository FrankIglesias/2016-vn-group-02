import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import DesignDreamTeamTime.IntervaloHorario;

public class TestDeManejoDeHoras {
	private IntervaloHorario unIntervalofacil;
	private IntervaloHorario unIntervalonocturno;
	private LocalTime hora1;
	private LocalTime hora2;
	private LocalTime hora3;

	@Before
	public void init() {
		unIntervalofacil = new IntervaloHorario(LocalTime.of(8, 00), LocalTime.of(17, 00));
		unIntervalonocturno = new IntervaloHorario(LocalTime.of(14, 00), LocalTime.of(3, 00));
		hora1 = LocalTime.of(14, 00);
		hora2 = LocalTime.of(8, 00);
		hora3 = LocalTime.of(2, 00);
	}

	@Test
	public void testIncluyeHoraenIntervaloFacil() {
		Assert.assertTrue(unIntervalofacil.incluyeHora(hora1));
	}

	@Test
	public void testNoIncluyeHoraenIntervaloFacil() {
		Assert.assertFalse(unIntervalofacil.incluyeHora(hora3));
	}

	@Test
	public void testIncluyeHoraEnLimiteDeIntervaloFacil() {
		Assert.assertTrue(unIntervalofacil.incluyeHora(hora2));
	}
	
	@Test
	public void testIncluyeHoraenIntervaloNocturno() {
		Assert.assertTrue(unIntervalonocturno.incluyeHora(hora3));
	}

	@Test
	public void testNoIncluyeHoraenIntervaloNocturno() {
		Assert.assertFalse(unIntervalonocturno.incluyeHora(hora2));
	}

	@Test
	public void testIncluyeHoraEnLimiteDeIntervaloNocturno() {
		Assert.assertTrue(unIntervalonocturno.incluyeHora(hora1));
	}
	
	
}
