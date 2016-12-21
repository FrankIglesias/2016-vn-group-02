package HashMapeameEsta;




import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import DesignDreamTeamTime.GestorIntervalos;

public class HashMapeameEsta {
	  List <Entry> listaDeElementos = new ArrayList<Entry>();
	     
	        public void put(DayOfWeek newKey, GestorIntervalos data){
	      Entry newEntry = new Entry(newKey, data);
	      if(!hasKey(newKey))  	  
	      listaDeElementos.add(newEntry);
  
	    }
	        public boolean hasKey(DayOfWeek key){
	        	return listaDeElementos.stream().filter(entry->entry.key == key).count()>0;
	        }
	    public GestorIntervalos get(DayOfWeek key){
	     	
			List<Entry> lista =  listaDeElementos.stream().filter(entry->entry.key == key).collect(Collectors.toList());
			if(lista.isEmpty())
				return null;
				else
			return lista.get(0).value;   
	    }

	 
	    public void  remove(DayOfWeek deleteKey){
	       listaDeElementos = listaDeElementos.stream().filter(entry-> entry.key!= deleteKey).collect(Collectors.toList());
	    }

		public Set<DayOfWeek> keySet() {
			return listaDeElementos.stream().map(entry-> entry.key).collect(Collectors.toSet());
		}
}
