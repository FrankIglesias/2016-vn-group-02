import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDeDisponibilidadDePOISegunFeriado {

	public Colectivo unColectivo;
	public Local miLocalCerrado;
	public Local miLocalAbierto;
	public CGP unCGPCerrado;
	public CGP unCGPAbierto;
	public Banco unBancoCerrado;
	public Banco unBancoAbierto;

	@Before
	public void init() {
		unColectivo = GlobalTestVariables.crearUnColectivo();
		miLocalCerrado = GlobalTestVariables.crearUnLocal(GlobalTestVariables.crearFeriadoNoAbierto());
		miLocalAbierto = GlobalTestVariables.crearUnLocal(GlobalTestVariables.crearFeriadoAbierto());
		unCGPCerrado = GlobalTestVariables.crearUnCGP(GlobalTestVariables.crearFeriadoNoAbierto());
		unCGPAbierto = GlobalTestVariables.crearUnCGP(GlobalTestVariables.crearFeriadoAbierto());
		unBancoCerrado = GlobalTestVariables.crearUnBanco(GlobalTestVariables.crearFeriadoNoAbierto());
		unBancoAbierto = GlobalTestVariables.crearUnBanco(GlobalTestVariables.crearFeriadoAbierto());
	}

	@Test
	public void ColectivoEstaEnFuncionamiento() {
		Assert.assertTrue(unColectivo.estaDisponible(LocalDateTime.now()));

	}

	@Test
	public void LocalCerradoEnFeriado() {
		Assert.assertFalse(miLocalCerrado.estaDisponible(LocalDateTime.now()));
	}

	@Test
	public void LocalAbiertoEnFeriado() {

		Assert.assertTrue(miLocalAbierto.estaDisponible(LocalDateTime.now()));
	}
	
	@Test
	public void CGPCerradoEnFeriado()	{
		Assert.assertFalse(unCGPCerrado.estaDisponible(LocalDateTime.now()));
	}
	
	@Test
	public void CGPAbiertoEnFeriado()	{
		Assert.assertTrue(unCGPAbierto.estaDisponible(LocalDateTime.now()));
	}
	
	@Test
	public void BancoCerradoEnFeriado()	{
		Assert.assertFalse(unBancoCerrado.estaDisponible(LocalDateTime.now()));
	}
	
	@Test
	public void BancoAbiertoEnFeriado()	{
		Assert.assertTrue(unBancoAbierto.estaDisponible(LocalDateTime.now()));
	}
	
	
}
