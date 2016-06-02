
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import BancoExterno.ApiDeBancoMock;
import MainClasses.Banco;
import MainClasses.CGP;
import MainClasses.Colectivo;
import MainClasses.POI;


public class EncuentraSegunPalabraClaveTest  {

	
	public Banco banco;
	public Banco banco2;
	public CGP cgp;
	public Colectivo colectivo;
	public List<POI> puntosDeInteres = new ArrayList<POI>();
	public Buscador buscador;
	String fraseABuscar;
	String fraseABuscar2;
	Terminal terminal = new Terminal("Terminal 1");

	@Before
	public void init() {
		buscador = new Buscador();
		banco = GlobalTestVariables.crearUnBanco(GlobalTestVariables.crearFeriadoVacio());
		cgp = GlobalTestVariables.crearUnCGP(null);
		ApiDeBancoMock.setRuta();
		banco2 = ApiDeBancoMock.obtenerBancoDesdeArchivo();
		colectivo = GlobalTestVariables.crearUnColectivo();

		fraseABuscar = "quiero un banco que tenga depositos";
		fraseABuscar2 = "Tarjeta vos";
		puntosDeInteres.add(colectivo);
		puntosDeInteres.add(banco);
		puntosDeInteres.add(banco2);
		puntosDeInteres.add(cgp);

		Buscador.setPuntosDeIntereses(puntosDeInteres);
		buscador.buscarSegunPalabraClave(fraseABuscar2, terminal);

	}

	@Test
	public void encontrarPOISegunPalabra() {
		Assert.assertEquals(buscador.buscarSegunPalabraClave(fraseABuscar, terminal).size(), 2);
		Assert.assertEquals("Cantidad de elementos en el array", 2, buscador.buscarSegunPalabraClave("depositos", terminal).size());
	}

	@Test
	public void noEncuentraPOISegunPalabraClave() {
		Assert.assertFalse(buscador.buscarSegunPalabraClave(fraseABuscar, terminal).contains(colectivo));
	}
	
	@Test
	public void encuentraACGP()
	{
		Assert.assertEquals(buscador.buscarSegunPalabraClave(fraseABuscar2, terminal).size(),1);
	}
	

}