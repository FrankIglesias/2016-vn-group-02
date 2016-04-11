
public class Direccion {
	private String callePrincipal;
	private String entreCalles;
	private int altura;
	private int piso;
	private int unidad;
	private int latitud;
	private int longitud;
	
	
	public Direccion (int latitud,int longitud) {
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
