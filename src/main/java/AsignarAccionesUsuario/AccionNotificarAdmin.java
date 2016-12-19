package AsignarAccionesUsuario;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import Repositorios.Terminal;

@Entity
@DiscriminatorValue (value  = "n")

public class AccionNotificarAdmin extends Accion {

	String mailAdmin;
	String mensaje;

	public AccionNotificarAdmin(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	public void ejecutarAccion(Terminal terminal) {
		terminal.enviarMailAlAdmin(mensaje, LocalDateTime.now(), terminal.getNombre());
		
	}
	
	@Override
	public int getId() {
		return this.id;
		
	}


}
