
public class Direccion {
	private String callePrincipal;
	private String entreCalles;
	private int altura;
	private int piso;
	private int unidad;
	private double latitud;
	private double longitud;
	private int comuna;
	
	public Direccion (double latitud,double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}
	

	public double getLatitud(){
		return this.latitud;
	}
		public double getLongitud(){
		return this.longitud;
	}	
}
