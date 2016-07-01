package AsignarAccionesUsuario;

import javax.mail.Message;

import GestorDeMail.GestorDeMailTrucho;
import GestorDeMail.GestorMailInterface;
import Repositorio.Usuario;

public class AccionNotificarAdmin implements Accion {
	String mailAdmin;
	
	public AccionNotificarAdmin(String mail) {
		super();
		this.mailAdmin = mail;
	}

	/*public void ejecutarAccion(Usuario usuario){
		usuario.quitar(accion);
		}
	
	public void ejecutarAccion(Usuario usuario) {
		GestorMailInterface gestorDeMail = new GestorDeMailTrucho();
		gestorDeMail.enviarMail(Message.RecipientType.TO, mailAdmin, "Tiempo Excedido", "");
	}*/

}
