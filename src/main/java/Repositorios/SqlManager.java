package Repositorios;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class SqlManager implements WithGlobalEntityManager  {

	public void registrar(Object objeto) {
		entityManager().persist(objeto);
	}

	/*
	 * public Cancha obtenerCancha(Long id){ return
	 * entityManager().find(Cancha.class, canchaId); } TODO
	 */
	
	
}
