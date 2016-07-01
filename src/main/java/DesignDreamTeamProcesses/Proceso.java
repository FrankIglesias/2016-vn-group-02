package DesignDreamTeamProcesses;

import java.util.Date;
import java.util.TimerTask;

public class Proceso {
	TimerTask proceso;
	Date date;

	public Proceso(TimerTask proceso, Date date) {
		this.proceso = proceso;
		this.date = date;
	}

	public TimerTask getProceso() {
		return proceso;
	}

	public void setProceso(TimerTask proceso) {
		this.proceso = proceso;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
