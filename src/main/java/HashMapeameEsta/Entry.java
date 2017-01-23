
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
	         @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	         GestorIntervalos value;
	         
	         public Entry() {
	        	 
	         }
	         
	         public Entry(DayOfWeek key,
	         GestorIntervalos value
	         ) {
				this.aKey = key;
				this.value = value;
			}

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public DayOfWeek getaKey() {
				return aKey;
			}

			public void setaKey(DayOfWeek aKey) {
				this.aKey = aKey;
			}

			public GestorIntervalos getValue() {
				return value;
			}

			public void setValue(GestorIntervalos value) {
				this.value = value;
			}

			public boolean tenesUnLunes() {
				return aKey.equals(DayOfWeek.MONDAY);
			}

			public boolean  tenesUnMartes() {
				return aKey.equals(DayOfWeek.TUESDAY);
			}

			public boolean tenesUnMiercoles() {
				return aKey.equals(DayOfWeek.WEDNESDAY);
			}

			public boolean tenesUnJueves() {
				return aKey.equals(DayOfWeek.THURSDAY);
			}

			public boolean tenesUnViernes() {
				return aKey.equals(DayOfWeek.FRIDAY);
			}

			public boolean  tenesUnSabado() {
				return aKey.equals(DayOfWeek.SATURDAY);
			}

			public boolean  tenesUnDomingo() {
				return aKey.equals(DayOfWeek.SUNDAY);
			}
			
			
			
	     }
	