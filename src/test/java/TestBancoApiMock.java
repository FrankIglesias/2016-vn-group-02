import org.junit.Assert;
import org.junit.Test;

public class TestBancoApiMock {
	
	@Test
	public void test() {
		Assert.assertEquals(ApiDeBancoMock.parse().nombre,"Banco de la Plaza");
	}

}
