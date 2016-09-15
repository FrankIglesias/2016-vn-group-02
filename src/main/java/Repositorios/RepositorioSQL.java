package Repositorios;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.hibernate.*;
import TypePois.*;

public class RepositorioSQL implements WithGlobalEntityManager{

	public void persistirObjeto(POI unObjeto){
		entityManager().persist(unObjeto);
	}
	
	public POI obtenerObjeto(Long id){
	
		return entityManager().find(POI.class, id);
		
	}

	/*public void persistirNuevoObjeto(POI objeto)
	{
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		//EntityTransaction tx = entityManager.getTransaction();
		//tx.begin();
		entityManager.persist(objeto);
		//tx.commit();
	}
	
	public Object obtenerObjeto(Long id)
	{
		return entityManager().find(Object.class, id);
	}
	
	public void actualizarObjeto(Object objeto){
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(objeto);
		tx.commit();
	}
	
	public void borrarObjeto(Object objeto){
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.remove(objeto);
		tx.commit();
	}*/
}
