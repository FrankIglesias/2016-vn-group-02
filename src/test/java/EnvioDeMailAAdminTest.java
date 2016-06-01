import javax.mail.Message;

import org.junit.Assert;
import org.junit.Test;

public class EnvioDeMailAAdminTest {
	
	@Test
	public void envioDeMailSatifactorio() {
		GestorDeMailTrucho gestorTrucho = new GestorDeMailTrucho();
		Assert.assertTrue(gestorTrucho.enviarMail(Message.RecipientType.TO, "mailprueba", "AsuntoPrueba", "CuerpoPrueba"));
	}

}
