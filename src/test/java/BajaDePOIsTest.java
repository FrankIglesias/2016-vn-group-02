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
		geo1 = new Geolocalizacion(-35.9338322, 72.348353, null, null);
		geo2 = new Geolocalizacion(-35.9566622, 72.566653, null, null);
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
		Assert.assertEquals(repo.size(), 1);
	}
}
