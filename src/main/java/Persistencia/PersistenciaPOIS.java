package Persistencia;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.hibernate.*;
import TypePois.*;

public class PersistenciaPOIS implements WithGlobalEntityManager{


	
	public void registrar(POI unPOI){
		entityManager().persist(unPOI);
	}
	
	public POI obtenerPOI(Long poiID){
		return entityManager().find(POI.class, poiID);
		
	}
	
	
}
