package AsignarAccionesUsuario;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.OneToOne;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import Repositorios.Terminal;

@Entity
@DiscriminatorValue (value  = "n")

public class AccionNotificarAdmin extends Accion {

	String mailAdmin;
	String mensaje;
	
	String nombre = "Notificar Administrador";


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

	@Override
	public String getNombre() {
		return nombre;
	}

}
