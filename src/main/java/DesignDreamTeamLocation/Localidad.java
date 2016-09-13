package DesignDreamTeamLocation;
import javax.persistence.*;

@Entity
public class Localidad {
	
	@Id
	@GeneratedValue
	@Column(name="id_localidad")
	private Long id;
	private String ciudad;
	private String provincia;
	private String pais;

	public Localidad(String unaCiudad, String unaProvincia, String unPais) {
		ciudad = unaCiudad;
		provincia = unaProvincia;
		pais = unPais;
	}

}
