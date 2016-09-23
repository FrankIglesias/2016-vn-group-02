package tests;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.quartz.JobExecutionException;

import DesignDreamTeamLocation.Geolocalizacion;
import Repositorios.RepoPOIs;
import TypePois.Banco;
import GobiernoDeLaCiudadExterno.DarDeBajaPOIMock;
import GobiernoDeLaCiudadExterno.DarDeBajaPOI;

public class TestMockDarDeBaja {

	RepoPOIs repo;
	Banco poi1;
	Banco poi2;
	Geolocalizacion geo1;
	Geolocalizacion geo2;
	DarDeBajaPOIMock mock;
	DarDeBajaPOI posta;
	int cantidadDePoiAntesDeModificacion;

	@Before
	public void init() {
		repo = RepoPOIs.getInstance();
		repo.inicializarPuntosDeIntereses();
		ArrayList<String> list = new ArrayList<String>();
		geo1 = new Geolocalizacion(48.2088611, 16.3698108, null, null);
		geo2 = new Geolocalizacion(16.3990259, 48.2146077, null, null);
		poi1 = new Banco(geo1, "poi1", list, null);
		poi2 = new Banco(geo2, "poi2", list, null);

		mock = new DarDeBajaPOIMock();
		posta = new DarDeBajaPOI();
	}

	@Test

	public void CargaOkTest() {
		cantidadDePoiAntesDeModificacion = repo.size();
		repo.agregarNuevosPoi(poi1);
		repo.agregarNuevosPoi(poi2);
		Assert.assertEquals(repo.size(), cantidadDePoiAntesDeModificacion + 2);
	}

	@Test
	public void BorrarDosPoiDeMockTest() throws JobExecutionException {
		repo.agregarNuevosPoi(poi1);
		repo.agregarNuevosPoi(poi2);
		cantidadDePoiAntesDeModificacion = repo.size();
		mock.execute (null);
		
		Assert.assertEquals(repo.size(), cantidadDePoiAntesDeModificacion - 2);
	}
	
	/*
	@Test
	public void BorrarDosPoiDePostaTest(){
		repo.agregarNuevosPoi(poi1);
		repo.agregarNuevosPoi(poi2);
		cantidadDePoiAntesDeModificacion = repo.size();
		posta.run();
		
		Assert.assertEquals(repo.size(), cantidadDePoiAntesDeModificacion - 2);
		*/ 
	//LO COMENTO PORQUE NO ANDA LA PAGINA
		

}
