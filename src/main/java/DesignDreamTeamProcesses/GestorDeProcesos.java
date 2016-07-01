package DesignDreamTeamProcesses;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.SortedSet;
import java.util.Timer;
import java.util.TreeSet;
import java.util.concurrent.Semaphore;

public class GestorDeProcesos {

	SortedSet<DesignDreamTeamProcess> listaDeProcesos = new TreeSet<>(Comparator.comparing(DesignDreamTeamProcess::getDate));
	Timer timer = new Timer();
	DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public final static Semaphore sem = new Semaphore(0);

	public void setProceso(DesignDreamTeamProcess procesoArealizar) {
		
		listaDeProcesos.add(procesoArealizar);
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

	private void correrProceso(DesignDreamTeamProcess unProceso) {
		System.out.println("Esperando la hora:  " + unProceso.getDate().getHours() + ":"
				+ unProceso.getDate().getMinutes() + ":" + unProceso.getDate().getSeconds() + "....");
		timer.schedule(unProceso, unProceso.getDate());
		SemVamoASincronizarno_wait();

	}
}