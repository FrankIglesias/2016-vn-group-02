package tests;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import DesignDreamTeamTime.IntervaloHorario;

public class TestDeManejoDeHoras {
	private IntervaloHorario unIntervalofacil;
	private IntervaloHorario unIntervalonocturno;
	private LocalDateTime hora1;
	private LocalDateTime hora2;
	private LocalDateTime hora3;

	@Before
	public void init() {
		unIntervalofacil = new IntervaloHorario(LocalDateTime.now().withHour(8).withMinute(00), LocalDateTime.now().withHour(17).withMinute(00));
		unIntervalonocturno = new IntervaloHorario(LocalDateTime.now().withHour(14).withMinute(00), LocalDateTime.now().withHour(3).withMinute(00));
		hora1 = LocalDateTime.now().withHour(14).withMinute(00);
		hora2 = LocalDateTime.now().withHour(8).withMinute(00);
		hora3 = LocalDateTime.now().withHour(2).withMinute(00);
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
