package Repositorio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class GestorDeProcesos {

	ArrayList<Timer> agendaDeProcesos = new ArrayList<Timer>();
	Timer timer = new Timer();
	DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public final static Semaphore sem = new Semaphore(0);
	public void setProceso(TimerTask actua,Date fecha) {
		System.out.println("Esperando la hora:  "+ fecha.getHours()+":"+fecha.getMinutes()+ "....");
		
		timer.schedule(actua,fecha);
		SemVamoASincronizarno();
	}



private void SemVamoASincronizarno(){
	
	try {
		sem.acquire();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}