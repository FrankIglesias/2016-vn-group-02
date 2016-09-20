package DesignDreamTeamLocation;
import javax.persistence.*;

@Embeddable
public class Localidad {
	
	private String ciudad;
	private String provincia;
	private String pais;

	public Localidad()
	{
		
	}
	public Localidad(String unaCiudad, String unaProvincia, String unPais) {
		super();
		ciudad = unaCiudad;
		provincia = unaProvincia;
		pais = unPais;
	}
	
	public String getCiudad()
	{
		return this.ciudad;
	}

}
