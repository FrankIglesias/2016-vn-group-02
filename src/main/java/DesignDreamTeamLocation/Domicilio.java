package DesignDreamTeamLocation;
import javax.persistence.*;

@Embeddable

public class Domicilio {

	
	private String callePrincipal;
	private String entreCalles;
	private String altura;
	private String piso;
	private String unidad;
	private String codigoPostal;
	private int comuna;
	
public Domicilio()
{
	
}

	public Domicilio(String callePrincipal, String entreCalles, String altura, String piso, String unidad,
			String codigoPostal,int comuna) {
		super();
		this.callePrincipal = callePrincipal;
		this.entreCalles = entreCalles;
		this.altura = altura;
		this.piso = piso;
		this.unidad = unidad;
		this.codigoPostal = codigoPostal;
		this.comuna = comuna;
	}
 


	public String getCallePrincipal() {
		return this.callePrincipal;
	}
	
	public int getComuna(){
		return comuna;
	}

}
