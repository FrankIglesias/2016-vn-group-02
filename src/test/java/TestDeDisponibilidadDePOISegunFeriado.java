import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDeDisponibilidadDePOISegunFeriado extends GlobalTestVariables {

	public Colectivo unColectivo;
	public Local miLocal;

	@Before
	public void init() {
		unColectivo = crearUnColectivo();
		miLocal = crearUnLocal();

	}

	@Test
	public void ColectivoEstaEnFuncionamiento() {
		Assert.assertTrue(unColectivo.estaDisponible(LocalDateTime.now()));

	}
	
	@Test
	public void LocalEstaCerradoPorqueEsFeriado() {
		Assert.assertFalse(miLocal.estaDisponible(LocalDateTime.now()));
	}
	
	
}
