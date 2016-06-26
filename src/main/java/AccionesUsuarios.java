import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccionesUsuarios {

	ArrayList<Acciones> listaDeAcciones = new ArrayList<Acciones>();
	List<Criterios> listaDeCriterios = new ArrayList<Criterios>();

	ArrayList<Usuario> seleccionarUsuarios(RepoUsuarios RepoUsuario) {
		return RepoUsuarios.getUsuarios().stream()
				.filter(unUsuario -> listaDeCriterios.stream()
						.allMatch(unCriterio -> unCriterio.esCumplidoPor(unUsuario)))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	// void cargarAcciones();

}