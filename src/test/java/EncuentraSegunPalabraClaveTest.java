import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EncuentraSegunPalabraClaveTest  {

	
	public Banco banco;
	public Banco banco2;
	public Colectivo colectivo;
	public List<POI> puntosDeInteres = new ArrayList<POI>();
	public Buscador buscador;
	String fraseABuscar;
	Terminal terminal = new Terminal("Terminal 1");

	@Before
	public void init() {
		buscador = new Buscador();
		banco = GlobalTestVariables.crearUnBanco(GlobalTestVariables.crearFeriadoVacio());
		ApiDeBancoMock.setRuta();
		banco2 = ApiDeBancoMock.obtenerBancoDesdeArchivo();
		colectivo = GlobalTestVariables.crearUnColectivo();

		fraseABuscar = "quiero un banco que tenga depositos";
		puntosDeInteres.add(colectivo);
		puntosDeInteres.add(banco);
		puntosDeInteres.add(banco2);

		Buscador.setPuntosDeIntereses(puntosDeInteres);
		buscador.buscarSegunPalabraClave(fraseABuscar, terminal);

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

}
