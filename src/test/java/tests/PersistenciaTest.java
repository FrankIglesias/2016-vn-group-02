package tests;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import TypePois.Banco;
import Persistencia.*;

public class PersistenciaTest extends AbstractPersistenceTest implements WithGlobalEntityManager{

private PersistenciaPOIS persistirPoi;

@Before
public void setUp()
{
	persistirPoi = new PersistenciaPOIS();
}

@Test
public void alPedirleQueGuardeUnPOIPersiste() {
	Banco unBanco = GlobalTestVariables.crearUnBanco(GlobalTestVariables.crearFeriadoVacio());
  persistirPoi.registrar(unBanco);  
  assertEquals(persistirPoi.obtenerCancha(unBanco.getId()), unBanco);
}

}
