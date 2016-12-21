package tests;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Repositorios.RepoPOIs;
import TypePois.Banco;
import TypePois.CGP;
import TypePois.Colectivo;
import TypePois.POI;

public class CargaDePrueba {
	
	RepoPOIs repoPois;
	List<POI> poisAPersistir;
	@Before
	public void init() {
		repoPois = RepoPOIs.getInstance();
		poisAPersistir = new ArrayList<POI>();
		Banco unBanco = GlobalTestVariables.crearUnBanco(GlobalTestVariables.crearFeriadoAbierto());
		unBanco.setUltimaFechaBusqueda(LocalDateTime.now().minusDays(10));
		
		Colectivo unColec = GlobalTestVariables.crearUnColectivo();
		unColec.setUltimaFechaBusqueda(LocalDateTime.now().minusDays(10));
		
		CGP unCGP = GlobalTestVariables.crearUnCGP(GlobalTestVariables.crearFeriadoAbierto());
		unCGP.setUltimaFechaBusqueda(LocalDateTime.now().minusDays(10));
		
		poisAPersistir.add(unBanco);
		poisAPersistir.add(unColec);
		poisAPersistir.add(unCGP);
		poisAPersistir.add(GlobalTestVariables.crearOtroCGP(GlobalTestVariables.crearFeriadoAbierto()));
		poisAPersistir.add(GlobalTestVariables.crearUnLocal(GlobalTestVariables.crearFeriadoAbierto()));
	}
	
	@Test
	public void cargarTodoEnMongo() {
		repoPois.limpiarMongo();
		poisAPersistir.stream().forEach(unPoi -> repoPois.persistirEnMongo(unPoi));
		repoPois.sincronizarBDs();
	}
	
	@Test
	public void llevarAMongoLoMasConsultado() {
		
	}
	
}
