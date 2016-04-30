import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDeDisponibilidadDePOISegunFeriado {

	public Colectivo unColectivo;
	public Local miLocal;
	public CGP unCGP;

	@Before
	public void init() {
		unColectivo = GlobalTestVariables.crearUnColectivo();
		miLocal = GlobalTestVariables.crearUnLocal();

	}

	@Test
	public void ColectivoEstaEnFuncionamiento() {
		Assert.assertTrue(unColectivo.estaDisponible(LocalDateTime.now()));

	}

	@Test
	public void LocalEstaCerradoPorqueEsFeriado() {
		Assert.assertFalse(miLocal.estaDisponible(LocalDateTime.now()));
	}

	@Test
	public void LocalEstaAbiertoPorqueEsFeriadoPeroAbreEnEseHorario() {

		Assert.assertTrue(miLocal.estaDisponible(LocalDateTime.of(2016, 07, 9, 01, 00)));
	}
}
