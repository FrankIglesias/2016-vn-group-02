package AsignarAccionesUsuario;

import Repositorio.Usuario;

public interface Criterio {

	boolean esCumplidoPor(Usuario unUsuario);

}
