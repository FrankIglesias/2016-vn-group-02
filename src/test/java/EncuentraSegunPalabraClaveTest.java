import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EncuentraSegunPalabraClaveTest  {

	public Banco banco;
	public Colectivo colectivo;
	public List<POI> puntosDeInteres = new ArrayList<POI>();
	public Buscador buscador;
	String fraseABuscar;
	Terminal terminal = new Terminal("Terminal 1");

	@Before
	public void init() {
		buscador = new Buscador();
		banco = GlobalTestVariables.crearUnBanco(GlobalTestVariables.crearFeriadoVacio());
		colectivo = GlobalTestVariables.crearUnColectivo();

		fraseABuscar = "quiero un banco malo";
		puntosDeInteres.add(colectivo);
		puntosDeInteres.add(banco);

		Buscador.setPuntosDeIntereses(puntosDeInteres);
		Buscador.buscarSegunPalabraClave(fraseABuscar, terminal);

	}

	@Test
	public void encontrarPOISegunPalabra() {

		Assert.assertTrue(Buscador.buscarSegunPalabraClave(fraseABuscar, terminal).contains(banco));
		Assert.assertEquals("Cantidad de elementos en el array", 1, Buscador.buscarSegunPalabraClave("banelco", terminal).size());
	}

	@Test
	public void noEncuentraPOISegunPalabraClave() {
		Assert.assertFalse(Buscador.buscarSegunPalabraClave(fraseABuscar, terminal).contains(colectivo));
	}

}
