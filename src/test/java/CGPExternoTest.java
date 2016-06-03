import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import MainClasses.Banco;
import MainClasses.CGP;
import MainClasses.POI;


public class CGPExternoTest {

	public Buscador buscador;
	public CGP cgp;
	public Banco banco;
	String fraseABuscar;
	Terminal terminal = new Terminal("Terminal 1");
	List<POI> listaDePOIs;
	

	@Before
	public void init() {
		cgp = GlobalTestVariables.crearUnCGP(null);
		banco = GlobalTestVariables.crearUnBanco(GlobalTestVariables.crearFeriadoVacio());
		fraseABuscar = "Quiero un POI de Direccion de Gestion Urbanistica";
		listaDePOIs = new ArrayList<POI>();
		listaDePOIs = RepositorioCGPExternoAdapter.obtenerCGPDesdeRepositorioExterno("4637-2355");
		listaDePOIs.add(cgp);
		listaDePOIs.add(banco);
		buscador = new Buscador();
		Buscador.setPuntosDeIntereses(listaDePOIs);
		buscador.buscarSegunPalabraClave(fraseABuscar, terminal);
	}

	@Test
	public void encontrarPOISegunPalabraClaveParaCGPExterno() {
		Assert.assertEquals(buscador.buscarSegunPalabraClave(fraseABuscar, terminal).size(), 1);
		
	}
	
	
}
