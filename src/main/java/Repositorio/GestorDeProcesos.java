package Repositorio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class GestorDeProcesos {

	ArrayList<Timer> agendaDeProcesos = new ArrayList<Timer>();

	DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/*
	 * ASI SE SETEA LA FECHA QUE QUIERAN
	 * try { 
	 * date = formatoFecha.parse("2014-02-04 06:33:00"); } 
	 * catch (ParseException e) 
	 * { e.printStackTrace();
	 * } 
	 */

	public void setProceso(TimerTask proceso, Date fecha) {
		Timer timer = new Timer();
		System.out.println("Esperando la hora:  "+ fecha.getHours()+":"+fecha.getMinutes()+ "....\n");
		timer.schedule(proceso, fecha);
		//agendaDeProcesos.add(timer);
	}
}
