
package HashMapeameEsta;
import java.time.DayOfWeek;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import DesignDreamTeamTime.GestorIntervalos;

@Entity
class Entry {
	
		@Id
		@GeneratedValue
		int id;
	
		//@Convert(converter=DayOfWeek.class)
	         DayOfWeek aKey;
	         @OneToOne(cascade = CascadeType.ALL)
	         GestorIntervalos value;
	         
	         
	         public Entry(DayOfWeek key,
	         GestorIntervalos value
	         ) {
				this.aKey = key;
				this.value = value;
			}
	     }
	