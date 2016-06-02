
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import DesignDreamTeamLocation.Domicilio;
import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamLocation.Localidad;
import MainClasses.Banco;
import MainClasses.CGP;
import MainClasses.Colectivo;
import MainClasses.Local;

public class CalculoDeCercaniaTest {
	private Banco banco;
	private Colectivo colectivo;
	private CGP centroDeCGP;
	private Local unLocal;

	private Geolocalizacion geolocalizacionPersonaCercanaColectivo;
	private Geolocalizacion geolocalizacionPersonaCercanaCGP;
	private Geolocalizacion geolocalizacionPersonaCercanaLocal;
	private Geolocalizacion geolocalizacionPersonaLejosDeTodo;
	private Domicilio domicilioPepe;
	private Localidad localidadPepe;

	@Before
	public void init() {

		banco = GlobalTestVariables.crearUnBanco(GlobalTestVariables.crearFeriadoVacio());
		colectivo = GlobalTestVariables.crearUnColectivo();
		centroDeCGP = GlobalTestVariables.crearUnCGP(GlobalTestVariables.crearFeriadoVacio());
		unLocal = GlobalTestVariables.crearUnLocal(GlobalTestVariables.crearFeriadoVacio());
		geolocalizacionPersonaCercanaColectivo = new Geolocalizacion(-34.5730926, -58.512000, domicilioPepe,
				localidadPepe);
		geolocalizacionPersonaCercanaCGP = new Geolocalizacion(-34.5730926, -58.5010000, domicilioPepe, localidadPepe);
		geolocalizacionPersonaCercanaLocal = new Geolocalizacion(-34.5730926, -58.5002200, domicilioPepe,
				localidadPepe);
		geolocalizacionPersonaLejosDeTodo = new Geolocalizacion(-34.5739926, -58.5992200, domicilioPepe, localidadPepe);

		domicilioPepe = new Domicilio("La Rioja", "San Juan y Humberto Primo", "1000", "", "", "1111");
		localidadPepe = new Localidad("Capital Federal", "Buenos Aires", "Argentina");
	}

	/*
	 * Todo testeado de aca http://www.movable-type.co.uk/scripts/latlong.html
	 */

	@Test
	public void calcularDistanciaEntreDosPOI() {

		Assert.assertEquals(banco.distanciaConUnPOI(centroDeCGP), 440, 100);
	}

	@Test
	public void calculoCercaniaConColectivo() {
		Assert.assertTrue(colectivo.estasCercaDeUnPunto(geolocalizacionPersonaCercanaColectivo));
	}

	@Test
	public void calculoCercaniaConCGP() {
		Assert.assertTrue(centroDeCGP.estasCercaDeUnPunto(geolocalizacionPersonaCercanaCGP));
	}

	@Test
	public void calculoCercaniaConLocal() {
		Assert.assertTrue(unLocal.estasCercaDeUnPunto(geolocalizacionPersonaCercanaLocal));
	}

	@Test
	public void calculoLejaniaConColectivo() {
		Assert.assertFalse(colectivo.estasCercaDeUnPunto(geolocalizacionPersonaLejosDeTodo));
	}

	@Test
	public void calculoLejaniaConCGP() {
		Assert.assertFalse(centroDeCGP.estasCercaDeUnPunto(geolocalizacionPersonaLejosDeTodo));
	}

	@Test
	public void calculoLejaniaConLocal() {
		Assert.assertFalse(unLocal.estasCercaDeUnPunto(geolocalizacionPersonaLejosDeTodo));
	}
}
