

import java.awt.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class POI {

	protected Direccion direccion;
	protected String nombre;
	protected ArrayList<String> palabrasClave = new ArrayList<String>();
	protected ArrayList<Horario> horario = new ArrayList<Horario>();

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
	
	public boolean estaDisponible(LocalDateTime horario){
		boolean retorno = false;
		
		for(int i = 0; i < this.horario.size(); i++) {
			if(this.horario.get(i).incluyeHorario(horario))
				retorno = true;
		}
		
		return retorno;
	}

	
}
