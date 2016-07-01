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
		geo1 = new Geolocalizacion(48.2088611, 16.3698108, null, null);
		geo2 = new Geolocalizacion(16.3990259, 48.2146077, null, null);
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
	public void RepoBrrarUnPOITest(){
		mock.run();
		System.out.println(repo.size());
		Assert.assertEquals(repo.size(), 0);
	}
	
	@Test
	public void testName() throws Exception {
		System.out.println(mock.obtenerStream());
	}
}
