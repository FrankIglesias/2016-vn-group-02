package AsignarAccionesUsuario;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import Repositorios.Terminal;

@Entity
@Inheritance
@DiscriminatorColumn (name = "discriminador")
@Table(name = "Accion")
public abstract class Accion {

	@Id
	@GeneratedValue
	@Column(name = "id_accion")
	protected Integer id;

	
	public int getID() {
		return id;
	}

	public abstract void ejecutarAccion(Terminal terminal);

	public abstract int getId();

	public abstract String getNombre();

}
