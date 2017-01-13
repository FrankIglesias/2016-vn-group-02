package HashMapeameEsta;




import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import DesignDreamTeamTime.GestorIntervalos;

@Entity
public class HashMapeameEsta {
	
	@Id
	@GeneratedValue
	int id;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	List <Entry> listaDeElementos = new ArrayList<Entry>();
	
	public boolean getTengoUnLunes() {
		return listaDeElementos.stream().anyMatch(unaEntry -> unaEntry.tenesUnLunes());
	}
	
	public boolean getTengoUnMartes() {
		return listaDeElementos.stream().anyMatch(unaEntry -> unaEntry.tenesUnMartes());
	}
	
	public boolean getTengoUnMiercoles() {
		return listaDeElementos.stream().anyMatch(unaEntry -> unaEntry.tenesUnMiercoles());
	}
	
	public boolean getTengoUnJueves() {
		return listaDeElementos.stream().anyMatch(unaEntry -> unaEntry.tenesUnJueves());
	}
	
	public boolean getTengoUnViernes() {
		return listaDeElementos.stream().anyMatch(unaEntry -> unaEntry.tenesUnViernes());
	}
	
	public boolean getTengoUnSabado() {
		return listaDeElementos.stream().anyMatch(unaEntry -> unaEntry.tenesUnSabado());
	}
	
	public boolean getTengoUnDomingo() {
		return listaDeElementos.stream().anyMatch(unaEntry -> unaEntry.tenesUnDomingo());
	}
	
	        public void put(DayOfWeek newKey, GestorIntervalos data){
	      Entry newEntry = new Entry(newKey, data);
	      if(!hasKey(newKey))  	  
	      listaDeElementos.add(newEntry);
  
	    }
	        public boolean hasKey(DayOfWeek key){
	        	return listaDeElementos.stream().filter(entry->entry.aKey == key).count()>0;
	        }
	        
	    public GestorIntervalos get(DayOfWeek key){
	     	
			List<Entry> lista =  listaDeElementos.stream().filter(entry->entry.aKey == key).collect(Collectors.toList());
			if(lista.isEmpty())
				return null;
				else
			return lista.get(0).value;   
	    }

	 
	    public void  remove(DayOfWeek deleteKey){
	       listaDeElementos = listaDeElementos.stream().filter(entry-> entry.aKey!= deleteKey).collect(Collectors.toList());
	    }

		public Set<DayOfWeek> keySet() {
			return listaDeElementos.stream().map(entry-> entry.aKey).collect(Collectors.toSet());
		}
		public List<Entry> getListaDeElementos() {
			return listaDeElementos;
		}
		public void setListaDeElementos(List<Entry> listaDeElementos) {
			this.listaDeElementos = listaDeElementos;
		}
}
