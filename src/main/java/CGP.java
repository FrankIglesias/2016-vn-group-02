import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class CGP extends POI {
	private int comuna;
	private List<Servicio> servicios = new ArrayList<Servicio>();

	public CGP(Direccion dir, String nombre, List<Servicio> listaDeServicios) {
		super(dir, nombre);
		servicios = listaDeServicios;
		for (Servicio unServicio : servicios)
			addPalabraClave(unServicio.getNombre());
	}

//	public boolean estaDisponible(LocalDateTime horario, Servicio servicio) {
//		try {
//			Servicio servicioBuscado = buscarServicio(servicio);
//			return (servicioBuscado.estasDisponibleEn(horario));
//		} catch (Exception e) {
//			System.out.println("Error! No existe el servicio buscado");
//			return false;
//		}
//	}

	public boolean estasCerca(Direccion unaDireccion) {
		return unaDireccion.getComuna() == (this.comuna);
	}

	private Servicio buscarServicio(Servicio servicio) {
		Servicio elServicioBuscado = servicios.stream().filter(unServicio -> equals(servicio)).findFirst().get();
		return elServicioBuscado;
	}

	@Override
	public boolean estaDisponible(LocalDateTime horario, Servicio servicio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean estaDisponible(LocalDateTime horario) {
		// TODO Auto-generated method stub
		return false;
	}

//	public boolean estaDisponible(LocalDateTime horario) {
//		return servicios.stream().anyMatch(servicio -> servicio.estasDisponibleEn(horario));
//	}
}
