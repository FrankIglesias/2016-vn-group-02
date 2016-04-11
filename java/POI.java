import java.util.ArrayList;
import java.util.List;

public abstract class POI {
protected Direccion direccion;
protected String nombre; 
protected List palabrasClave = new ArrayList<String>();

	public POI(Direccion dir,String nombre){
		this.direccion = dir;
		this.nombre = nombre;
		}
	
	public float distanciaHacia(POI unPoi){
		distanciaEntre (direccion , unPoi.direccion);
	}
	/*
	
	public float distanciaEntre (Direcion direccion1 , Direccion direccion2){
		double longitud = distancia (direccion1.getLongitud(), direccion2.getLongitud());
		double latitud = distancia (direccion1.getLatitud(), direccion2.getLatitud());
		longitud = toRadian(longitud);
		latitud = toRadian(latitud);
		double a = Math.pow(Math.sin(latitud/2), 2) + 
				    Math.cos(direccion1.getLatitud()) * Math.cos(direccion2.getLatitud())* Math.pow(Math.sin(longitud/2), 2);
		longitud = Math.pow(longitud, 2);
		latitud = Math.pow(latitud, 2);
		double c = 2 . Math.tan(sqrt(a), sqrt(1-a));
		double d = ;

				c = 2 · atan2(√a, √(1−a))

				d = R · c
	}
	
	public double distancia(double num, double num2){
		return num - num2;
	}
	
	static double toRadian(double valor){
		return (Math.PI / 180) * valor;
	}
 */
}
