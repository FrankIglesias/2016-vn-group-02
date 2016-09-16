package AsignarAccionesUsuario;

import javax.persistence.Column;
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
	@Column(name="id_accion")
	protected int id;
	
	
	public void setId(int unID)
	{
		this.id = unID;
	}
	
	public int getID()
	{
		return id;
	}
	public abstract void ejecutarAccion(Usuario usuario);
	public abstract int getId();

}
