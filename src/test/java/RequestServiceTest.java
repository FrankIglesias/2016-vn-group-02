import com.sun.jersey.api.client.ClientResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by niko118 on 5/1/16.
 */
public class RequestServiceTest {

    RequestService requester;

    @Before
    public void setUp() throws Exception {
        this.requester = new RequestService();
    }

    @Test
    public void obtenerConUnFiltro() throws Exception {
        //Se solicita todos los datos de un libro por su isbn.
        ClientResponse response = this.requester.getBookByFilter("isbn","9706434526");
        assertEquals(response.getStatus(), 200);
        String json = response.getEntity(String.class);
        //assertTrue(json.contains("items"));
        //assertTrue(json.contains("Tio Tom"));
    }

  
}
