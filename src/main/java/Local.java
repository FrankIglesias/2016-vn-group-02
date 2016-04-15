

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Local extends POI {

	private Rubro rubro;
	private String nombre;

	public Local(String nombreDelRubro, Direccion dir, String nombre, ArrayList<Horario> horario, int radioDeCercania) {
		super(dir, nombre);
		rubro = new Rubro(nombreDelRubro, horario, radioDeCercania);
		addPalabraClave(nombreDelRubro);
	}

	public Direccion getDireccion(){
		return direccion;
	}
	public Local(String nombre, Direccion dir, Rubro rubro) { // en caso que el													// rubro exista
		super(dir, nombre);
		this.rubro = rubro;
	}

//	public boolean estaDisponible(GregorianCalendar horario, Servicio servicio) {
//		return this.estaDisponible(horario);
//	}

//	public boolean estaDisponible(LocalDateTime horario) {
//		return rubro.estaDisponible(horario);
//	}

	public boolean estasCerca(Direccion unaDireccion) {
		return rubro.estasCerca(unaDireccion, this);
	}

	@Override
	public boolean estaDisponible(LocalDateTime horario, Servicio servicio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean estaDisponible(LocalDateTime horario) {
		// TODO Auto-generated method stub
		return false;
	}
}
