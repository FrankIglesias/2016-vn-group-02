package TypePois;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamTime.Feriado;
import DesignDreamTeamTime.HorarioYDia;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="Pois")
public abstract class POI {
	
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="id_poi")
	private int id;
	
	@OneToOne
	@JoinColumn(name="geolocalizacion")
	private Geolocalizacion point;
	
	@Column(name="nombre_poi")
	private String nombre;
	
	
	@ElementCollection
	@CollectionTable(name="palabrasClaves", joinColumns=@JoinColumn(name="id_poi"))
	public List<String> palabrasClave = new ArrayList<String>();
	
	@OneToOne
	protected HorarioYDia horario = new HorarioYDia();
	
	@OneToMany
	@JoinColumn(name="id_poi")
	protected List<Feriado> feriados = new ArrayList<Feriado>();
	
	public void setId(int unID)
	{
		this.id = unID;
	}
	
	public int getId()
	{
		return this.id;
	}

	public POI()
	{};
	public POI(Geolocalizacion point, String nombre, ArrayList<String> palabrasClave, HorarioYDia horario,
			List<Feriado> feriados) {
		super();
		this.setPoint(point);
		this.setNombre(nombre);
		this.setPalabrasClave(palabrasClave);
		this.addPalabrasClaves(nombre);
		this.horario = horario;
		this.feriados = feriados;
	}

	public double distanciaConUnPOI(POI unPoi) {
		return this.getPoint().distanciaCon(unPoi.getPoint());
	}
	public boolean tenesTodasLasPalabrasClaves(ArrayList<String> palabrasClaves)
	{
		return(palabrasClaves.stream().allMatch(unaPalabraClave -> this.palabrasClave.contains(unaPalabraClave)));
	}

	public boolean estasCercaDeUnPunto(Geolocalizacion point) {
		return distanciaMenor(this.getPoint().distanciaCon(point), 500);
	}

	public boolean distanciaMenor(double distancia, double rango) {
		return distancia < rango;
	}

	public boolean tenesUnaPalabraDe(String unaFrase) {
		List<String> listaDePalabrasDeFrase = Arrays.asList(unaFrase.split(" "));
		return getPalabrasClave().stream().anyMatch(palabra -> estaEnListaDePalabras(palabra, listaDePalabrasDeFrase));
	}

	private boolean estaEnListaDePalabras(String palabra, List<String> lista) {
		return lista.stream().anyMatch(unaPalabra -> unaPalabra.contains(palabra));
	}

	public void addPalabrasClaves(String unaFrase) {
		String[] fraseDividida = unaFrase.split(" ");
		for (String palabra : fraseDividida) {
			this.getPalabrasClave().add(palabra);
		}
	}

	public void addFeriado(Feriado unFeriado) {
		this.feriados.add(unFeriado);
	}

	public boolean sosFeriado(LocalDate fecha) {
		return (feriados.stream().anyMatch(unFeriado -> unFeriado.comparateConDiaYMes(fecha)));
	}

	public boolean compararmeConHorarioDeUnFeriado(Feriado unFeriado, LocalDateTime horario) {
		return (unFeriado.incluisHorario(horario.toLocalTime()));
	}

	public Feriado getUnFeriado(LocalDate fecha) {
		return (feriados.stream().filter(unFeriado -> unFeriado.comparateConDiaYMes(fecha)))
				.collect(Collectors.toList()).get(0);
	}

	public boolean tenesFeriados() {
		return (!feriados.isEmpty());
	}

	public boolean estaDisponible(LocalDateTime horarioPreguntado) {

		if (tenesFeriados() && sosFeriado(horarioPreguntado.toLocalDate())) {
			return compararmeConHorarioDeUnFeriado(getUnFeriado(horarioPreguntado.toLocalDate()), horarioPreguntado);
		} else
			return (horario.incluyeHorario(horarioPreguntado));

	}

	public Geolocalizacion getPoint() {
		return point;
	}

	public void setPoint(Geolocalizacion point) {
		this.point = point;
	}

	public String getNombre() {
		return nombre;
	}
	

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<String> getPalabrasClave() {
		return palabrasClave;
	}

	public void setPalabrasClave(ArrayList<String> palabrasClave) {
		this.palabrasClave = palabrasClave;
	}

}
