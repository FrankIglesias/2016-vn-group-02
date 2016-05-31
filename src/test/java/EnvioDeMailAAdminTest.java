import javax.mail.Message;

import org.junit.Assert;
import org.junit.Test;

public class EnvioDeMailAAdminTest {
	
	@Test
	public void envioDeMailSatifactorio() {
		GestorDeMailTrucho gestorTrucho = new GestorDeMailTrucho();
		Assert.assertTrue(gestorTrucho.enviarMail(Message.RecipientType.TO, "fransciscoj.iglesias@hotmail.com", "Puto", "Entrega el orto por 6 jorgitos"));
	}

}
