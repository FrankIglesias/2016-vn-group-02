package DesignDreamTeamErrorHandlers;

import java.util.Date;

import javax.mail.Message;

import GestorDeMail.GestorDeMailTrucho;

public class ErrorEnviarMailAlAdministrador implements DDTErrorHandler {

	private GestorDeMailTrucho gestorDeMail = new GestorDeMailTrucho();
	private String mailAdmin = "mailprueba@gmail.com";

	@Override
	public void ejecutarAccion(Date fecha, Runnable proceso) {
		gestorDeMail.enviarMail(Message.RecipientType.TO, mailAdmin, "Ha ocurrido un error en el sistema, " + fecha,
				"");

	}

}
