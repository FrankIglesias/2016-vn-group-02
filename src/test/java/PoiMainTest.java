import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

public class PoiMainTest {
	protected Colectivo colectivo;
	protected Banco banco;
	protected Direccion direccion1;
	protected Direccion direccion2;
	protected CGP centroDeCGP;
	protected List<Servicio> serviciosDelCGP = new ArrayList<Servicio>();
	protected Horario horarioDelServicio1;
	protected Servicio ventaDeVOS;

	@Before 
	public void init(){  
	
		direccion1 = new Direccion(-34.596044,-58.419946,2);
		direccion2 = new Direccion(-34.602945,-58.420948,2);
		colectivo = new Colectivo(direccion1,"134",46);
		banco = new Banco(direccion2,"Banelco");
		horarioDelServicio1 = new Horario(1, 6, 12, 15);
		ventaDeVOS = new Servicio("Tarjeta vos", horarioDelServicio1);
		serviciosDelCGP.add(ventaDeVOS);
		
		
		
		
	}
}
