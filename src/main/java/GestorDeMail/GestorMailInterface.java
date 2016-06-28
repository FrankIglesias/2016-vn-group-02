package GestorDeMail;
import javax.mail.Message;

public interface GestorMailInterface {

	public boolean enviarMail(Message.RecipientType tipoDeCopia, String usuarioTo, String asunto, String cuerpoDelMail);

	public int getContadorDeMails();

}
