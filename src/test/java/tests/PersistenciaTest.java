package tests;
import javax.persistence.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import DesignDreamTeamLocation.Domicilio;
import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamLocation.Localidad;

import org.hibernate.*;
import TypePois.Banco;
import Persistencia.*;
import Repositorios.RepositorioSQL;

public class PersistenciaTest extends AbstractPersistenceTest implements WithGlobalEntityManager{

private PersistenciaPOIS persistirPoi;
private Domicilio domicilioBanco;
private Localidad localidadBanco;
private Geolocalizacion geolocalizacionBanco;
private ArrayList<String> palabrasClavesBanco;


@Before
public void setUp()
{
	persistirPoi = new PersistenciaPOIS();
	domicilioBanco = new Domicilio("Basavilbaso", "Guido y Eva Peron", "1420", "0", "0", "1824", 1);
	localidadBanco = new Localidad("Lanus", "Buenos Aires", "Argentina");
	geolocalizacionBanco = new Geolocalizacion(10,46,domicilioBanco, localidadBanco);
	palabrasClavesBanco = new ArrayList<String>();
	palabrasClavesBanco.add("plata");
	palabrasClavesBanco.add("dinero");
}

@Test
public void alPedirleQueGuardeUnPOIPersiste() {
	Banco unBanco = new Banco();
	unBanco.setNombre("Banquito");
	unBanco.setId(1L);
	//unBanco.setPalabrasClave(palabrasClavesBanco);
	//unBanco.setPoint(geolocalizacionBanco);
	
  	persistirPoi.registrar(unBanco);

  assertEquals(persistirPoi.obtenerPOI(unBanco.getId()), unBanco);
}

@Test
public void alObtenerUnPOICoincideConElPersistido()
{
	Banco unBanco = new Banco();
	unBanco.setNombre("Mi Banco");
	unBanco.setId(2L);
	persistirPoi.registrar(unBanco);
	Banco otroBanco = (Banco) persistirPoi.obtenerPOI(2L);
	
	Assert.assertTrue(otroBanco.getNombre() == "Mi Banco");
	
}

@Test
public void nuevoTest()
{
	Banco unBanco = new Banco();
	unBanco.setPoint(geolocalizacionBanco);
	unBanco.setId(3L);
	
	persistirPoi.registrar(unBanco);
	
	Banco otroBanco = (Banco) persistirPoi.obtenerPOI(3L);
	
	Assert.assertTrue(otroBanco.getPoint().getLatitud() == 10);
}


}
