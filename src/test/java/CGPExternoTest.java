import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import DesignDreamTeamLocation.Geolocalizacion;

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
	public void init()
	{
	//cgp = GlobalTestVariables.crearUnCGP(null);
	//fraseABuscar = "Tarjeta Vos";
	List<POI> listaDePOIs = RepositorioCGPExternoAdapter.obtenerCGPDesdeRepositorioExterno("Jose Campanella");
	//banco = GlobalTestVariables.crearUnBanco(GlobalTestVariables.crearFeriadoVacio());
	//listaDePOIs.add(cgp);
	//listaDePOIs.add(banco);
	//buscador = new Buscador();
	//Buscador.setPuntosDeIntereses(listaDePOIs);
	//buscador.buscarSegunPalabraClave(fraseABuscar, terminal);
	}
	
	@Test
	public void encontrarPOISegunPalabraClaveParaCGPExterno() {
		Assert.assertEquals(listaDePOIs.size(), 0);	
	
	
}
}
