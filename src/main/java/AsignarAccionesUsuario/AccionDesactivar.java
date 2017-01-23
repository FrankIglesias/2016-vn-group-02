package AsignarAccionesUsuario;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import Repositorios.Terminal;

@Entity
@DiscriminatorValue (value = "d")
public class AccionDesactivar extends Accion {

	@OneToOne
	Accion accionAquitar;
	
	String nombre = "Desactivar una accion";
	
	public AccionDesactivar(Accion accion) {
		super();
		this.accionAquitar = accion;
	}

	public void ejecutarAccion(Terminal terminal) {
		terminal.quitar(accionAquitar);
		terminal.quitar(this);
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
