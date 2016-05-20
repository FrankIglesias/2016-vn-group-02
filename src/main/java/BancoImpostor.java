import java.util.ArrayList;
import java.util.List;

public class BancoImpostor extends Banco{

private static List<Servicio> servicios;
private String nombre;

public BancoImpostor(String unNombre, List<Servicio> listaServicios) {
		super(null,unNombre,null,null);
		servicios = listaServicios;

		
	}

public void setNombre(String unNombre)
{
	this.nombre = unNombre;
}

public String getNombre()
{
	return this.nombre;
}

public boolean realizasUnServicio(String nombreServicio)
{
	return(servicios.stream().anyMatch(unServicio -> unServicio.getNombre() == nombreServicio));
	
}
}
