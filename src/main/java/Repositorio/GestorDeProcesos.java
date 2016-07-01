package Repositorio;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.SortedSet;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;
import java.util.concurrent.Semaphore;

public class GestorDeProcesos {

	SortedSet<Proceso> listaDeProcesos = new TreeSet<>(Comparator.comparing(Proceso::getDate));
	Timer timer = new Timer();
	DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public final static Semaphore sem = new Semaphore(0);

	public void setProceso(TimerTask actua, Date fecha) {
		Proceso unProceso = new Proceso(actua,fecha);
		listaDeProcesos.add(unProceso);
	}

	private void SemVamoASincronizarno_wait() {

		try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void correrProcesos() {
		listaDeProcesos.forEach(unProceso -> correrProceso(unProceso));
	}
	
	public Date setFecha(String fecha){
		Date date = null;
		try {
			date = formatoFecha.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
	}

	private void correrProceso(Proceso unProceso) {
		System.out.println("Esperando la hora:  " + unProceso.getDate().getHours() + ":"
				+ unProceso.getDate().getMinutes() + ":" + unProceso.getDate().getSeconds() + "....");
		timer.schedule(unProceso.getProceso(), unProceso.getDate());
		SemVamoASincronizarno_wait();

	}
}