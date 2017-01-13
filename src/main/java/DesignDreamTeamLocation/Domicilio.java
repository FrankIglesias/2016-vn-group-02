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
	public String getEntreCalles() {
		return this.entreCalles;
	}
	public String getPiso() {
		return piso;
	}
	public String getUnidad() {
		return unidad;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public String getAltura() {
		return this.altura;
	}
	
	public int getComuna(){
		return comuna;
	}

	public void setComuna(int comuna){
		this.comuna = comuna;
	}
}
