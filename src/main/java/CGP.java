import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class CGP extends POI {
	private int comuna;
	private List<Servicio> servicios = new ArrayList<Servicio>();

	public CGP(Direccion dir, String nombre, List<Servicio> listaDeServicios) {
		super(dir, nombre);
		servicios = listaDeServicios;
		for (Servicio unServicio : servicios)
			addPalabraClave(unServicio.getNombre());
	}

	public boolean estasCerca(Direccion unaDireccion) {
		return unaDireccion.getComuna() == (this.comuna);
	}

	private Servicio buscarServicio(Servicio servicio) {
		Servicio elServicioBuscado = servicios.stream().filter(unServicio -> equals(servicio)).findFirst().get();
		return elServicioBuscado;
	}

	@Override
	public boolean estaDisponible(LocalDateTime horario) {
		boolean retorno = false;

		Scanner keyboard = new Scanner(System.in);
		System.out.println("Buscar Servicio:");
		String busqueda = keyboard.nextLine();

		if (busqueda.equals("")) {
			for (int i = 0; i < this.servicios.size(); i++) {
				if (servicios.get(i).estaDisponible())
					retorno = true;
			}
		} else {
			boolean encontrado = false;
			
			for (int i = 0; i < this.servicios.size(); i++) {
				if (servicios.get(i).getNombre().equals(busqueda) && servicios.get(i).estaDisponible()){
					retorno = true;
					encontrado = true;
				}
			}
			
			if(!encontrado)
			{
				System.out.println("No existe el servicio "+busqueda);
			}
		}

		return retorno;
	}
}
