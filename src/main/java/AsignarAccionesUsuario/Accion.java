package AsignarAccionesUsuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import Repositorios.Usuario;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Accion")
public abstract class Accion {
	
	@Id
	@GeneratedValue
	protected int id;
	
	public abstract void ejecutarAccion(Usuario usuario);
	public abstract int getId();

}
