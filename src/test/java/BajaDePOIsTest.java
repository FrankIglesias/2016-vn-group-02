import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import DesignDreamTeamLocation.Geolocalizacion;
import GobiernoDeLaCiudadExterno.DarDeBajaPOIMock;
import GobiernoDeLaCiudadExterno.POIGCBAdapter;
import Repositorios.RepoPOIs;

public class BajaDePOIsTest {
	RepoPOIs repo;
	POIGCBAdapter poi1;
	POIGCBAdapter poi2;
	Geolocalizacion geo1;
	Geolocalizacion geo2;
	DarDeBajaPOIMock mock;
	int cantidadDePoiAntesDeModificacion;
	@Before
	public void init() {
		repo = RepoPOIs.getInstance();
		repo.inicializarPuntosDeIntereses();
		geo1 = new Geolocalizacion(48.2088623046875, 16.369810104370117, null, null);
		geo2 = new Geolocalizacion(16.399024963378906, 48.21460723876953, null, null);
		poi1 = new POIGCBAdapter(geo1,"poi1");
		poi2 = new POIGCBAdapter(geo2,"poi2");
		mock = new DarDeBajaPOIMock(null, null); 
	} 
	
	@Test
	public void RepoCargarDosPOITest(){
	cantidadDePoiAntesDeModificacion = repo.size();
		repo.agregarNuevosPoi(poi1);
		repo.agregarNuevosPoi(poi2);
		Assert.assertEquals(repo.size(), cantidadDePoiAntesDeModificacion+2);
	}
	
	@Test
	public void RepoBorrarDosPOITest(){
		repo.agregarNuevosPoi(poi1);
		repo.agregarNuevosPoi(poi2);
		cantidadDePoiAntesDeModificacion= repo.size();
		mock.run();
		Assert.assertEquals(repo.size(), cantidadDePoiAntesDeModificacion -2);
	}
	
	/*@Test
	public void RestJsonCorrectamente() throws Exception {
		String json = mock.obtenerStream();
		Assert.assertNotEquals("latitud", json);
	}*/
}
