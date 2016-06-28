package GestorDeMail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public abstract class GestorDeMail implements GestorMailInterface {

	private String usuarioFrom;
	private String contrasenia;

	private Session session;

	public GestorDeMail(Session session, String usuarioFrom, String contrasenia) {
		this.session = session;
		this.usuarioFrom = usuarioFrom;
		this.contrasenia = contrasenia;
	}

	public boolean enviarMail(Message.RecipientType tipoDeCopia, String usuarioTo, String asunto,
			String cuerpoDelMail) {
		
	
		MimeMessage message = new MimeMessage(session);
		try {
			
			System.out.println("HOLI");
			message.setFrom(new InternetAddress(usuarioFrom));
			message.addRecipient(tipoDeCopia, new InternetAddress(usuarioTo));
			message.setSubject(asunto);
			message.setText(cuerpoDelMail);
			Transport t = session.getTransport(session.getProperties().getProperty("protocol"));
			t.connect(usuarioFrom, contrasenia);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			
			return true;
			
		} catch (MessagingException me) {
			return false;
		}

	}

}
