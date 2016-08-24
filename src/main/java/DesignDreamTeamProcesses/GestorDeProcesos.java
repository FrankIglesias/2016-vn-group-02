package DesignDreamTeamProcesses;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.SortedSet;
import java.util.Timer;
import java.util.TreeSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GestorDeProcesos {
	SortedSet<DesignDreamTeamProcess> listaDeProcesos = new TreeSet<>(
			Comparator.comparing(DesignDreamTeamProcess::getDate));
	Timer timer = new Timer();
	DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

	public void setProceso(DesignDreamTeamProcess procesoArealizar) {
		listaDeProcesos.add(procesoArealizar);
	}

	public void correrProcesos() {

		listaDeProcesos.forEach(unProceso -> executor.schedule(unProceso,
				obtenerIntervaloEnSegundos(unProceso.date.getTime()), TimeUnit.SECONDS));
	}

	private long obtenerIntervaloEnSegundos(long tiempo) {
		Date actual = new Date();
		long var = tiempo - actual.getTime();
		if (var < 0)
			return 0;
		else
			return var / 1000;

	}

}