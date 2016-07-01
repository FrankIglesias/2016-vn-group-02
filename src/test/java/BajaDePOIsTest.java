import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import DesignDreamTeamLocation.Geolocalizacion;
import Repositorio.DarDeBajaPOIMock;
import Repositorio.RepoPOIs;
import TypePois.genericPOI;

public class BajaDePOIsTest {
	RepoPOIs repo;
	genericPOI poi1;
	genericPOI poi2;
	Geolocalizacion geo1;
	Geolocalizacion geo2;
	DarDeBajaPOIMock mock;
	
	@Before
	public void init() {
		repo = RepoPOIs.getInstance();
		geo1 = new Geolocalizacion(48.2088623046875, 16.369810104370117, null, null);
		geo2 = new Geolocalizacion(16.399024963378906, 48.21460723876953, null, null);
		poi1 = new genericPOI(geo1,"poi1");
		poi2 = new genericPOI(geo2,"poi2");
		mock = new DarDeBajaPOIMock();
	}
	
	@Test
	public void RepoCargarDosPOITest(){
		repo.agregarNuevosPoi(poi1);
		repo.agregarNuevosPoi(poi2);
		Assert.assertEquals(repo.size(), 2);
	}
	
	@Test
	public void RepoBorrarDosPOITest(){
		mock.run();
		Assert.assertEquals(repo.size(), 0);
	}
	
	@Test
	public void RestJsonCorrectamente() throws Exception {
		String json = mock.obtenerStream();
		Assert.assertNotEquals("", json);
	}
}
