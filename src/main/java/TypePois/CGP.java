package TypePois;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamTime.Feriado;
import DesignDreamTeamTime.HorarioYDia;


@Entity
public class CGP extends POI {
	
	
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="idPoi")
	private List<Servicio> servicios = new ArrayList<Servicio>();

	public CGP()
	{
		
	}
	public CGP(Geolocalizacion point, String nombre, List<Servicio> servicios, List<Feriado> feriados) {
		super(point, nombre, new ArrayList<String>(), new HorarioYDia(), feriados);
		this.servicios = servicios;
		palabrasClave.add("CGP");
		servicios.stream().forEach(servicio -> addPalabrasClaves(servicio.getNombre()));
		this.addPalabrasClaves(point.getDomicilio().getCallePrincipal());

	}

	public boolean estasCercaDeUnPunto(Geolocalizacion otroPoint) {
		return distanciaMenor(getPoint().distanciaCon(otroPoint), 500);
	}
	public List<Servicio> getServicios()
	{
		return this.servicios;
	}
	public Servicio buscarServicio(String nombreDelServicio) {
		Servicio elServicioBuscado = servicios.stream()
				.filter(unServicio -> unServicio.getNombre().equals(nombreDelServicio)).findFirst().get();
		return elServicioBuscado;

	}
public boolean listaVacia(){
	return servicios.isEmpty();
}
	public boolean estaDisponible(LocalDateTime horario) {

		return servicios.stream().anyMatch(unServicio -> unServicio.estaDisponible(horario));
	}
	@Override
	public String getTipo() {
		return "cgp";
	}
}
