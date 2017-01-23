package AsignarAccionesUsuario;

import java.util.ArrayList;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import DesignDreamTeamProcesses.DesignDreamTeamProcess;
import Repositorios.RepoTerminales;
import Repositorios.Terminal;

public class AsignarAccionesUsuarios extends DesignDreamTeamProcess {

	private RepoTerminales repoTerminales;
	private Accion accion;
	private Criterio criterio=null;
	private List<Terminal> terminalesAsignadas = new ArrayList<Terminal>();
	
	public static AsignarAccionesUsuarios instancia =null;

	public AsignarAccionesUsuarios(Criterio criterio, Accion accion) {
		this.repoTerminales = RepoTerminales.getInstance();
		this.accion = accion;
		this.criterio = criterio;
	}
	
	public AsignarAccionesUsuarios() {
		
	}
	public static AsignarAccionesUsuarios getInstance() {
		if (instancia == null) {
			instancia = new AsignarAccionesUsuarios();
		}
		return instancia;
	}

	public void setCriterio(Criterio criterio) {
		this.criterio = criterio;
	}
	
	public void setAccion(Accion accion) {
		this.accion = accion;
	}
	
	
	public ArrayList<Terminal> seleccionarTerminales(RepoTerminales repoTerminales) {
		return repoTerminales.seleccionaUsuarios(this);

	}

	public boolean cumpleCriterio(Terminal unaTerminal) {
		return criterio.esCumplidoPor(unaTerminal);
	}

	public void asignarAcciones() {
		terminalesAsignadas = seleccionarTerminales(repoTerminales);
		terminalesAsignadas.forEach(unaTerminal -> unaTerminal.addAccion(accion));
	}

	public void ejecutarAccion(Accion unaAccion) {
		terminalesAsignadas.stream().forEach((unaTerminal -> unaTerminal.ejecutaUnaAccion(unaAccion)));
	}
	
	public void analizarAccionesParaUnaTerminal(Terminal unTerminal) {
		if(!(criterio == null)) {
		if(cumpleCriterio(unTerminal)) {
			unTerminal.ejecutarTodasLasAcciones();
		}
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Asignando Acciones al usuario.");
		this.asignarAcciones();
		System.out.println("Realizado Correctamente");
	}
}
