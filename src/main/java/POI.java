import java.util.ArrayList;
import java.util.Date;


public abstract class POI {

		public static void main(String[] args) {

			
			Direccion direc1 = new Direccion(40.7486, -73.9864);
			Direccion direc2 = new Direccion(38.1424, -72.1245);
		
			double dist;
			
			dist = distanciaEntre (direc1, direc2);
			
			//prueba distancia
			System.out.println(dist);
			
		}

		protected Direccion direccion;
		protected String nombre; 
		protected ArrayList<String> palabrasClave = new ArrayList<String>();

		public abstract boolean estaDisponible(Date horario, Servicio servicio);
		public abstract boolean estaDisponible(Date horario);
		
		public POI(Direccion dir, String nombre) 
		{
		this.direccion = dir;
		this.nombre = nombre;
		}

	public void distanciaHacia(POI unPoi) {
		// distanciaEntre (direccion , unPoi.direccion); 
	}


	
	
	

	public static double distancia(double num, double num2) {
		return num - num2;
	}
	
	public boolean tenesUnaPalabra(String unaPalabra) {
			return palabrasClave.stream().anyMatch(palabra -> palabra.equals(unaPalabra));
	}
	
	
	public static double distanciaEntre(Direccion direccion1, Direccion direccion2) {
	    double earthRadius = 6371000; //meters
	    double lat1 = direccion1.getLatitud();
		double lat2 = direccion2.getLatitud();
		double lng1 = direccion1.getLongitud();
		double lng2 =  direccion2.getLongitud();

	    double dLat = Math.toRadians(lat2-lat1);
	    double dLng = Math.toRadians(lng2-lng1);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double dist = (double) (earthRadius * c);

	    return dist;
	    
		 }
	}




