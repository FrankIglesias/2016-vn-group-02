import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDeDisponibilidadDePOISegunFeriado {

	public Colectivo unColectivo;
	public Local miLocalCerrado;
	public Local miLocalAbierto;
	public CGP unCGP;

	@Before
	public void init() {
		unColectivo = GlobalTestVariables.crearUnColectivo();
		miLocalCerrado = GlobalTestVariables.crearUnLocal(GlobalTestVariables.crearFeriadoNoAbierto());;
		miLocalAbierto = GlobalTestVariables.crearUnLocal(GlobalTestVariables.crearFeriadoAbierto());
	}

	@Test
	public void ColectivoEstaEnFuncionamiento() {
		Assert.assertTrue(unColectivo.estaDisponible(LocalDateTime.now()));

	}

	@Test
	public void LocalEstaCerradoPorqueEsFeriado() {
		Assert.assertFalse(miLocalCerrado.estaDisponible(LocalDateTime.now()));
	}

	@Test
	public void LocalEstaAbiertoPorqueEsFeriadoPeroAbreEnEseHorario() {

		Assert.assertTrue(miLocalAbierto.estaDisponible(LocalDateTime.of(2016, 07, 9, 01, 00)));
	}
}
