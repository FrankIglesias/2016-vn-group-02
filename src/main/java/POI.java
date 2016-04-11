import java.util.ArrayList;
import java.util.List;

public abstract class POI {

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			
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

	

	public POI(Direccion dir,String nombre){
		this.direccion = dir;
		this.nombre = nombre;
		}

	
	public void distanciaHacia(POI unPoi){
		//distanciaEntre (direccion , unPoi.direccion);
	}
	
	
	public static double distanciaEntre (Direccion direccion1 , Direccion direccion2){
		
		double lat1 = direccion1.getLatitud();
		double lat2 = direccion2.getLatitud();
		double diferenciaLong = Math.toRadians(distancia(direccion1.getLongitud(), direccion2.getLongitud()));
		double diferenciaLat = Math.toRadians(distancia(lat1, lat2));
		
		double radio = 6371000;
		
		double a = Math.sin(diferenciaLat/2) * Math.sin(diferenciaLat/2) +
				 Math.cos(lat1) * Math.cos(lat2) * Math.sin(diferenciaLong/2) * 
				 Math.sin(diferenciaLong/2);
		
		double b = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		double distancia = radio * b;
		
		return distancia;
		
	}
	
	public static double distancia(double num, double num2){
		return num - num2;
	}
	
	static double toRadian(double valor){
		return (Math.PI / 180) * valor;
	}
 

}
