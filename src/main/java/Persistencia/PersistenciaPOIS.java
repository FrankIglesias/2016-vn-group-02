package Persistencia;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import TypePois.*;

public class PersistenciaPOIS implements WithGlobalEntityManager{

	
	public void registrar(Banco unPOI){
		entityManager().persist(unPOI);
	}
	
	public Banco obtenerCancha(Long poiID){
		return entityManager().find(Banco.class, poiID);
	}
}
