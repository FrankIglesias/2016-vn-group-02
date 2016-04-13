package src.main.java;

import java.util.GregorianCalendar;

public class Colectivo extends POI {

	private Integer linea;
 
	
	public Colectivo(Direccion dir, String nombre, int numero) {
		super(dir, nombre);
		linea = numero;
		this.addPalabraClave(linea.toString());
		}


	public boolean estaDisponible(GregorianCalendar horario, Servicio servicio) {
		return true;
	}
	public boolean estasCerca(Direccion unaDireccion) {
		return this.direccion.distanciaCon(unaDireccion)< 100;
	}

	public boolean estaDisponible(GregorianCalendar horario) {
		return true;
	}

}
