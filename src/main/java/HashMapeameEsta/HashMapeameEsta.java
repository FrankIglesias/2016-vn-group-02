package HashMapeameEsta;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import DesignDreamTeamTime.GestorIntervalos;

@Entity
public class HashMapeameEsta {

	@Id
	@GeneratedValue
	int id;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	List<Entry> listaDeElementos = new ArrayList<Entry>();

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

	public String getHastaLunes() {
	
		if(getTengoUnLunes()) 
			return listaDeElementos.stream().filter(unaEntry -> unaEntry.tenesUnLunes()).collect(Collectors.toList()).get(0)
					.getValue().getIntervalosHorarios().get(0).getHoraFin().withSecond(0).toString();
		
		return null;
	}
	
	public String getHastaMartes() {
	
		if(getTengoUnMartes()) 
			return listaDeElementos.stream().filter(unaEntry -> unaEntry.tenesUnMartes()).collect(Collectors.toList()).get(0)
					.getValue().getIntervalosHorarios().get(0).getHoraFin().withSecond(0).toString();
		
		return null;
	}
	
	public String getHastaMiercoles() {
	
		if(getTengoUnMiercoles()) 
			return listaDeElementos.stream().filter(unaEntry -> unaEntry.tenesUnMiercoles()).collect(Collectors.toList()).get(0)
					.getValue().getIntervalosHorarios().get(0).getHoraFin().withSecond(0).toString();
		
		return null;
	}
	
	public String getHastaJueves() {

		if(getTengoUnJueves()) 
			return listaDeElementos.stream().filter(unaEntry -> unaEntry.tenesUnJueves()).collect(Collectors.toList()).get(0)
					.getValue().getIntervalosHorarios().get(0).getHoraFin().withSecond(0).toString();
		
		return null;
	}
	
	public String getHastaViernes() {

		if(getTengoUnViernes()) 
			return listaDeElementos.stream().filter(unaEntry -> unaEntry.tenesUnViernes()).collect(Collectors.toList()).get(0)
					.getValue().getIntervalosHorarios().get(0).getHoraFin().withSecond(0).toString();
		
		return null;
	}
	
	public String getHastaSabado() {

		if(getTengoUnSabado()) 
			return listaDeElementos.stream().filter(unaEntry -> unaEntry.tenesUnSabado()).collect(Collectors.toList()).get(0)
					.getValue().getIntervalosHorarios().get(0).getHoraFin().withSecond(0).toString();
		
		return null;
	}
	
	public String getHastaDomingo() {

		if(getTengoUnDomingo()) 
			return listaDeElementos.stream().filter(unaEntry -> unaEntry.tenesUnDomingo()).collect(Collectors.toList()).get(0)
					.getValue().getIntervalosHorarios().get(0).getHoraFin().withSecond(0).toString();
		
		return null;
	}
	
	public String getDesdeLunes() {
		
		if(getTengoUnLunes()) 
			return listaDeElementos.stream().filter(unaEntry -> unaEntry.tenesUnLunes()).collect(Collectors.toList()).get(0)
					.getValue().getIntervalosHorarios().get(0).getHoraInicio().withSecond(0).toString();
		
		return null;
	}
	
	public String getDesdeMartes() {
	
		if(getTengoUnMartes()) 
			return listaDeElementos.stream().filter(unaEntry -> unaEntry.tenesUnMartes()).collect(Collectors.toList()).get(0)
					.getValue().getIntervalosHorarios().get(0).getHoraInicio().withSecond(0).toString();
		
		return null;
	}
	
	public String getDesdeMiercoles() {
	
		if(getTengoUnMiercoles()) 
			return listaDeElementos.stream().filter(unaEntry -> unaEntry.tenesUnMiercoles()).collect(Collectors.toList()).get(0)
					.getValue().getIntervalosHorarios().get(0).getHoraInicio().withSecond(0).toString();
		
		return null;
	}
	
	public String getDesdeJueves() {

		if(getTengoUnJueves()) 
			return listaDeElementos.stream().filter(unaEntry -> unaEntry.tenesUnJueves()).collect(Collectors.toList()).get(0)
					.getValue().getIntervalosHorarios().get(0).getHoraInicio().withSecond(0).toString();
		
		return null;
	}
	
	public String getDesdeViernes() {

		if(getTengoUnViernes()) 
			return listaDeElementos.stream().filter(unaEntry -> unaEntry.tenesUnViernes()).collect(Collectors.toList()).get(0)
					.getValue().getIntervalosHorarios().get(0).getHoraInicio().withSecond(0).toString();
		
		return null;
	}
	
	public String getDesdeSabado() {

		if(getTengoUnSabado()) 
			return listaDeElementos.stream().filter(unaEntry -> unaEntry.tenesUnSabado()).collect(Collectors.toList()).get(0)
					.getValue().getIntervalosHorarios().get(0).getHoraInicio().withSecond(0).toString();
		
		return null;
	}
	
	public String getDesdeDomingo() {

		if(getTengoUnDomingo()) 
			return listaDeElementos.stream().filter(unaEntry -> unaEntry.tenesUnDomingo()).collect(Collectors.toList()).get(0)
					.getValue().getIntervalosHorarios().get(0).getHoraInicio().withSecond(0).toString();
		
		return null;
	}

	public void put(DayOfWeek newKey, GestorIntervalos data) {
		Entry newEntry = new Entry(newKey, data);
		if (!hasKey(newKey))
			listaDeElementos.add(newEntry);

	}

	public boolean hasKey(DayOfWeek key) {
		return listaDeElementos.stream().filter(entry -> entry.aKey == key).count() > 0;
	}

	public GestorIntervalos get(DayOfWeek key) {

		List<Entry> lista = listaDeElementos.stream().filter(entry -> entry.aKey == key).collect(Collectors.toList());
		if (lista.isEmpty())
			return null;
		else
			return lista.get(0).value;
	}

	public void remove(DayOfWeek deleteKey) {
		listaDeElementos = listaDeElementos.stream().filter(entry -> entry.aKey != deleteKey)
				.collect(Collectors.toList());
	}

	public Set<DayOfWeek> keySet() {
		return listaDeElementos.stream().map(entry -> entry.aKey).collect(Collectors.toSet());
	}

	public List<Entry> getListaDeElementos() {
		return listaDeElementos;
	}

	public void setListaDeElementos(List<Entry> listaDeElementos) {
		this.listaDeElementos = listaDeElementos;
	}
}
