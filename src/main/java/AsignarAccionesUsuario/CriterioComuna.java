package AsignarAccionesUsuario;

import Repositorio.Usuario;

public class CriterioComuna implements Criterio {
		int comuna;

		public boolean esCumplidoPor(Usuario unUsuario) {
			if (unUsuario.getComuna() == comuna){
				return true;
			}
			return false;
		}
		
		
}
