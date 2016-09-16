package AsignarAccionesUsuario;

import javax.mail.Message;
import javax.persistence.Entity;
import javax.persistence.Transient;

import GestorDeMail.GestorDeMailTrucho;
import GestorDeMail.GestorMailInterface;
import Repositorios.Usuario;

@Entity
public class AccionNotificarAdmin extends Accion {

	String mailAdmin;

	public AccionNotificarAdmin(String mail) {
		super();
		this.mailAdmin = mail;
	}

	public void ejecutarAccion(Usuario usuario) {
		GestorMailInterface gestorDeMail = new GestorDeMailTrucho();
		gestorDeMail.enviarMail(Message.RecipientType.TO, mailAdmin, "Tiempo Excedido", "");
	}

}
