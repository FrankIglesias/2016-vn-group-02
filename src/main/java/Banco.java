import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/*Date date = new Date();   // given date
Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
calendar.setTime(date);   // assigns calendar to given date 
calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
calendar.get(Calendar.HOUR);        // gets hour in 12h format
calendar.get(Calendar.MONTH);       // gets month number, NOTE this is zero based!*/

public class Banco extends POI {

/*	public enum Dia {
		// public abstract String getNombre();
		LUNES {

			@Override
			public String getNombre() {
				return"Lunes";
			}
		},
		MARTES {

			@Override
			public String getNombre() {
				return"Martes";
			}

		},
		MIERCOLES {

			@Override
			public String getNombre() {
				return"Miercoles";
			}

		},
		JUEVES{

		@Override
		public String getNombre() {
			return "Jueves";
			}
		},
		VIERNES

		{

		@Override
		public String getNombre() {
			return "Viernes";
		}

	};*/

	public Banco(Direccion dir, String nombre) {
		super(dir, nombre);

	}

	public boolean estaDisponible(Date horario) {
		// Date date = new Date(); // given date
		Calendar calendar = GregorianCalendar.getInstance(); // creates a new
		calendar.setTime(horario); // assigns calendar to given date
		Integer hora = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h
		Integer dia = calendar.get(Calendar.DAY_OF_WEEK);
		return ((10<hora && hora<15) && (1 < dia && dia <5));
	}
	
	
	/*
	 * Date diaReferencia = new Date();
	 * 
	 * SimpleDateFormat formateador = new SimpleDateFormat("dd.MM.yyyy"); String
	 * fechadeReferencia=formateador.format(diaReferencia);
	 */

	public boolean estaDisponible(Date horario, Servicio servicio) {
		return true;
	}

}
