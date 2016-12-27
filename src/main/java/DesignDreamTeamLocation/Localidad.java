package DesignDreamTeamLocation;
import javax.persistence.*;

@Entity

public class Localidad {
	
	@Id
	@GeneratedValue
	@Column(name="idLocalidad")
	private int id;
	
	private String ciudad;
	private String provincia;
	private String pais;

	public Localidad()
	{
		
	}
	public int getID()
	{
		return this.id;
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

	public String getProvincia()
	{
		return this.provincia;
	}
	
	public String getPais()
	{
		return this.pais;
	}
}
