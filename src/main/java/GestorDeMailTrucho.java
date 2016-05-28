import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GestorDeMailTrucho implements GestorMailInterface {

	public boolean enviarMail(Message.RecipientType tipoDeCopia, String usuarioTo, String asunto,
			String cuerpoDelMail) {

		try {
			Properties props = new Properties();
			Session session = Session.getDefaultInstance(props);

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(""));
			message.addRecipient(tipoDeCopia, new InternetAddress(usuarioTo));
			message.setSubject(asunto);
			message.setText(cuerpoDelMail);

	
			//Transport t = session.getTransport("");
			//t.close();
			
		} catch (MessagingException me){
			return false;
		}
		return true;
	}
}
