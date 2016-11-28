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
import Repositorios.RepoUsuarios;
import Repositorios.Usuario;

@Entity
@Table(name = "Acciones_manager")


public class AsignarAccionesUsuarios extends DesignDreamTeamProcess {
	@Id
	private Long id;
	@Transient
	private RepoUsuarios repoUsuario;
	@OneToOne
	private Accion accion;
	@Transient
	private Criterio criterio;
	@Transient
	private List<Usuario> usuariosAsignados = new ArrayList<Usuario>();

	public AsignarAccionesUsuarios(RepoUsuarios repoUsuario, Criterio criterio, Accion accion) {
		this.repoUsuario = repoUsuario;
		this.accion = accion;
		this.criterio = criterio;
	}

	public ArrayList<Usuario> seleccionarUsuarios(RepoUsuarios repoUsuario) {
		return repoUsuario.seleccionaUsuarios(this);

	}

	public boolean cumpleCriterio(Usuario unUsuario) {
		return criterio.esCumplidoPor(unUsuario);
	}

	public void asignarAcciones() {
		usuariosAsignados = seleccionarUsuarios(repoUsuario);
		usuariosAsignados.forEach(usuario -> usuario.addAccion(accion));
	}

	public void ejecutarAccion(Accion unaAccion) {
		usuariosAsignados.stream().forEach(unUsuario -> unUsuario.ejecutaUnaAccion(unaAccion));
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Asignando Acciones al usuario.");
		this.asignarAcciones();
		System.out.println("Realizado Correctamente");
	}
}
