package GestorDeMail;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GestorDeMailTrucho implements GestorMailInterface {

	int contadorDeMails = 0;

	public boolean enviarMail(Message.RecipientType tipoDeCopia, String usuarioTo, String asunto,
			String cuerpoDelMail) {

		try {
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.user", "mailx@gmail.com");
			props.setProperty("mail.smtp.auth", "true");

			Session session = Session.getDefaultInstance(props);

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("mailx@gmail.com"));
			message.addRecipient(tipoDeCopia, new InternetAddress(usuarioTo));
			message.setSubject(asunto);
			message.setText(cuerpoDelMail);

			Transport t = session.getTransport("smtp");
			t.close();

			contadorDeMails = contadorDeMails + 1;

		} catch (MessagingException me) {
			return false;
		}
		return true;
	}

	public int getContadorDeMails() {
		return contadorDeMails;
	}
}
