package tests;
import javax.persistence.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import DesignDreamTeamLocation.Domicilio;
import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamLocation.Localidad;

import org.hibernate.*;
import TypePois.Banco;
import Repositorios.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
public class PersistenciaTest extends AbstractPersistenceTest implements WithGlobalEntityManager{

private RepoPOIs repositorioPOI;
private Domicilio domicilioBanco;
private Localidad localidadBanco;
private Geolocalizacion geolocalizacionBanco;
private ArrayList<String> palabrasClavesBanco;


@Before
public void setUp()
{
	repositorioPOI = new RepoPOIs();
	domicilioBanco = new Domicilio("Basavilbaso", "Guido y Eva Peron", "1420", "0", "0", "1824", 1);
	localidadBanco = new Localidad("Lanus", "Buenos Aires", "Argentina");
	geolocalizacionBanco = new Geolocalizacion(10,46,domicilioBanco, localidadBanco);
	palabrasClavesBanco = new ArrayList<String>();
	palabrasClavesBanco.add("plata");
	palabrasClavesBanco.add("dinero");
}


/*@Test
public void contextUp() {
	assertNotNull(entityManager());
}

@Test
public void contextUpWithTransaction() throws Exception {
	withTransaction(() -> {});
}*/

@Test
public void alPedirleQueGuardeUnPOIPersiste() {	
	Banco unBanco = new Banco();
	unBanco.setNombre("Banquito");
	unBanco.setId(1L);
	//unBanco.setPalabrasClave(palabrasClavesBanco);
	unBanco.setPoint(geolocalizacionBanco);

  	repositorioPOI.persistirObjeto(unBanco);

  assertEquals(repositorioPOI.obtenerObjeto(unBanco.getId()), unBanco);
}

@Test
public void alObtenerUnPOICoincideConElPersistido()
{

	Banco unBanco = new Banco();
	unBanco.setNombre("Mi Banco");
	unBanco.setId(1L);
	repositorioPOI.persistirObjeto(unBanco);
	Banco otroBanco = (Banco) repositorioPOI.obtenerObjeto(1L);
	Assert.assertTrue(otroBanco.getNombre() == "Mi Banco");
	
}

@Test
public void nuevoTest()
{
	Banco unBanco = new Banco();
	unBanco.setNombre("Mi otro Banco");
	unBanco.setPoint(geolocalizacionBanco);
	unBanco.setId(1L);
	
	repositorioPOI.persistirObjeto(unBanco);

	Banco otroBanco = (Banco) repositorioPOI.obtenerObjeto(1L);
	
	Assert.assertTrue(otroBanco.getPoint().getLatitud() == 10);
	Assert.assertTrue(otroBanco.getNombre() == "Mi otro Banco");
}



}
