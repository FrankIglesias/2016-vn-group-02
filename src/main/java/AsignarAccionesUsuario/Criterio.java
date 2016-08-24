package AsignarAccionesUsuario;

import Repositorios.Usuario;

public interface Criterio {

	boolean esCumplidoPor(Usuario unUsuario);

}
