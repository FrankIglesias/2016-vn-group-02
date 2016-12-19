package AsignarAccionesUsuario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import DesignDreamTeamProcesses.DesignDreamTeamProcess;
import Repositorios.RepoTerminales;
import Repositorios.Terminal;

@Entity
@Table(name = "Acciones_manager")


public class AsignarAccionesUsuarios extends DesignDreamTeamProcess {
	@Id
	private Long id;
	@Transient
	private RepoTerminales repoTerminales;
	@OneToOne
	private Accion accion;
	@Transient
	private Criterio criterio;
	@Transient
	private List<Terminal> terminalesAsignadas = new ArrayList<Terminal>();

	public AsignarAccionesUsuarios(Criterio criterio, Accion accion) {
		this.repoTerminales = RepoTerminales.getInstance();
		this.accion = accion;
		this.criterio = criterio;
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
		terminalesAsignadas.stream().forEach(unUsuario -> unUsuario.ejecutaUnaAccion(unaAccion));
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Asignando Acciones al usuario.");
		this.asignarAcciones();
		System.out.println("Realizado Correctamente");
	}
}
