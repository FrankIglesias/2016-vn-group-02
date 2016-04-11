
public class Direccion {
	private String callePrincipal;
	private String entreCalles;
	private int altura;
	private int piso;
	private int unidad;
	private double latitud;
	private double longitud;
	
	
	public Direccion (double latitud,double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
	public double distanciaCon(Direccion dir){
		double earthRadius = 6371000; // radio expresado en metros
		double lat2 = dir.getLatitud();
		double lng2 = dir.getLongitud();

		// formula de Haversine
		double dLat = Math.toRadians(lat2 - latitud);
		double dLng = Math.toRadians(lng2 - longitud);
		double a = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(Math.toRadians(latitud))
				 * Math.cos(Math.toRadians(lat2))  * Math.pow(Math.sin(dLng / 2),2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double dist = (double) (earthRadius * c);

		// retorna distancia en metros
		return dist;
	}
	
	public double getLatitud(){
		return this.latitud;
	}
		public double getLongitud(){
		return this.longitud;
	}
}