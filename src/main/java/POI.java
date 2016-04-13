package src.main.java;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;

public abstract class POI {

	protected Direccion direccion;
	protected String nombre;
	protected ArrayList<String> palabrasClave = new ArrayList<String>();
	protected Horario horario;
	public abstract boolean estaDisponible(GregorianCalendar horario, Servicio servicio);
	public abstract boolean estaDisponible(GregorianCalendar horario);

	public POI(Direccion dir, String nombre) {
		this.direccion = dir;
		this.nombre = nombre;
		this.addPalabraClave(nombre);
	}

	
	public double distanciaCon(POI unPoi) {
		return this.direccion.distanciaCon(unPoi.direccion);
	}
	
	
	public boolean estasCerca(Direccion unaDireccion) {
		return this.direccion.distanciaCon(unaDireccion)< 500;
	}

	public boolean tenesUnaPalabraDe(String unaFrase) {
		String[] split = unaFrase.split(" ");
		return palabrasClave.stream().anyMatch(palabra -> Arrays.asList(split).contains(palabra));
		
		
	}

	public void addPalabraClave(String unaPalabra) {
		this.palabrasClave.add(unaPalabra);
	}

	
}
