package DesignDreamTeamErrors;

import java.util.Date;
import java.util.TimerTask;

import javax.mail.Message;

import GestorDeMail.GestorDeMailTrucho;

public class ErrorEnviarMailAlAdministrador implements ErrorHandler {

	private GestorDeMailTrucho gestorDeMail = new GestorDeMailTrucho();
	private String mailAdmin = "mailprueba@gmail.com";


	@Override
	public void ejecutarAccion(Date fecha, TimerTask proceso) {
		gestorDeMail.enviarMail(Message.RecipientType.TO, mailAdmin, "Ha ocurrido un error en el sistema", "");
		
	}
	
		
}
