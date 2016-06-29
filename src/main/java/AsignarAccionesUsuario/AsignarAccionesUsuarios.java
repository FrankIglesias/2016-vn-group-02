package AsignarAccionesUsuario;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;

import Repositorio.RepoUsuarios;
import Repositorio.Usuario;

public class AsignarAccionesUsuarios extends TimerTask {

	ArrayList<Acciones> listaDeAcciones = new ArrayList<Acciones>();
	List<Criterios> listaDeCriterios = new ArrayList<Criterios>();

	ArrayList<Usuario> seleccionarUsuarios(RepoUsuarios RepoUsuario) {
		return RepoUsuarios.getUsuarios().stream()
				.filter(unUsuario -> listaDeCriterios.stream()
						.allMatch(unCriterio -> unCriterio.esCumplidoPor(unUsuario)))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	// void cargarAcciones();

}