import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Repositorio.Buscador;
import Repositorio.RepositorioCGPExternoAdapter;
import Repositorio.Terminal;
import TypePois.Banco;
import TypePois.CGP;
import TypePois.POI;

import java.util.ArrayList;


public class CGPExternoTest {

	public Buscador buscador;
	public CGP cgp;
	public CGP cgp2;
	public Banco banco;
	String fraseABuscar;
	String fraseABuscar2;
	Terminal terminal = new Terminal("Terminal 1");
	List<POI> listaDePOIs;
	
	

	@Before
	public void init() {
		cgp = GlobalTestVariables.crearUnCGP(null);
		cgp2 = GlobalTestVariables.crearOtroCGP(null);
		banco = GlobalTestVariables.crearUnBanco(GlobalTestVariables.crearFeriadoVacio());
		fraseABuscar = "Quiero un POI de Direccion de Gestion Urbanistica";
		fraseABuscar2 = "Quiero un CGP que este cerca o sobre la Av. Rivadavia";
		listaDePOIs = new ArrayList<POI>();
		listaDePOIs = RepositorioCGPExternoAdapter.obtenerCGPDesdeRepositorioExterno("4637-2355");
		listaDePOIs.add(cgp);
		listaDePOIs.add(cgp2);
		listaDePOIs.add(banco);
		buscador = new Buscador();
		Buscador.setPuntosDeIntereses(listaDePOIs);
		
		
		
		
	}

	@Test
	public void encuentraSoloAlPOIExternoSegunPalabraClave() {
		Assert.assertEquals(buscador.buscarSegunPalabraClave(fraseABuscar, terminal).size(), 1);
	}
	
	@Test
	public void encuentraMasDeUnPOIExternoYNoExternoSegunPalabraClave()
	{
		Assert.assertEquals(buscador.buscarSegunPalabraClave(fraseABuscar2, terminal).size(), 2);
	}
	
	
	

	
}
