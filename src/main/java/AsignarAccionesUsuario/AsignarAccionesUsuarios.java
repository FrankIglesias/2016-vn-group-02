package AsignarAccionesUsuario;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;

import Repositorio.RepoUsuarios;
import Repositorio.Usuario;

public class AsignarAccionesUsuarios extends TimerTask {

	ArrayList<Accion> listaDeAcciones = new ArrayList<Accion>();
	List<Criterio> listaDeCriterios = new ArrayList<Criterio>();

	ArrayList<Usuario> seleccionarUsuarios(RepoUsuarios RepoUsuario) {
		return RepoUsuarios.getUsuarios().stream()
				.filter(unUsuario -> listaDeCriterios.stream()
						.allMatch(unCriterio -> unCriterio.esCumplidoPor(unUsuario)))
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	

	@Override
	public void run() {
		
		
	}

	// void cargarAcciones();

}