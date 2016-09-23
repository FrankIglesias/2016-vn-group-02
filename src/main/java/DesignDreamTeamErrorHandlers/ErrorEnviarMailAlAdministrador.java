package DesignDreamTeamErrorHandlers;

import java.time.LocalDate;

import javax.mail.Message;

import org.quartz.Job;
import org.quartz.JobExecutionException;

import GestorDeMail.GestorDeMailTrucho;

public class ErrorEnviarMailAlAdministrador extends DDTErrorHandler {

	private GestorDeMailTrucho gestorDeMail = GestorDeMailTrucho.getInstance();
	private String mailAdmin = "mailprueba@gmail.com";

	@Override
	public void ejecutarAccion(Job proceso, Exception e) throws JobExecutionException {
		super.ejecutarAccion(null, e);
		gestorDeMail.enviarMail(Message.RecipientType.TO, mailAdmin, "Ha ocurrido un error en el sistema, " + LocalDate.now(),
				"");
	}

}
