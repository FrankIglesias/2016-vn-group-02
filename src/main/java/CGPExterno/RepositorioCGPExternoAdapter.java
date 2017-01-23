package CGPExterno;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import DesignDreamTeamLocation.Domicilio;
import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamTime.GestorIntervalos;
import DesignDreamTeamTime.HorarioYDia;
import DesignDreamTeamTime.IntervaloHorario;
import HashMapeameEsta.HashMapeameEsta;
import TypePois.CGP;
import TypePois.POI;
import TypePois.Servicio;


public class RepositorioCGPExternoAdapter {
	public static ArrayList<POI> obtenerCGPDesdeRepositorioExterno(String criterio) {
		ArrayList<POI> pois = new ArrayList<POI>();
		RepositorioCGPExterno repositorio = new RepositorioCGPExterno();
		List<CentroDTO> listaCentros = repositorio.buscarCGPs(criterio);
		listaCentros.forEach(unCentro -> agregarAListaDeCGP(pois, unCentro));
		return pois;

	}

	public static void agregarAListaDeCGP(ArrayList<POI> listaPOIS, CentroDTO unCentro) {
		String calleDeDomicilioCentro = null;
		String alturaDeDomicilioCentro;
		String[] domicilioDeCentro = unCentro.getDomicilio().split(" ");
		int alturaDomicilio = domicilioDeCentro.length - 1;

		for (int i = 0; i < alturaDomicilio; i++) {
			calleDeDomicilioCentro += " " + domicilioDeCentro[i];
		}
		alturaDeDomicilioCentro = domicilioDeCentro[alturaDomicilio];

		Domicilio domicilioCGP = new Domicilio(calleDeDomicilioCentro, null, alturaDeDomicilioCentro, null, null, null, alturaDomicilio);
		Geolocalizacion geolocalizacionCGP = new Geolocalizacion(unCentro.getLatitud(), unCentro.getLongitud(),
				domicilioCGP, null);
		List<Servicio> serviciosCGP = obtenerServiciosDeCGPExterno(unCentro);
		CGP unCGP = new CGP(geolocalizacionCGP, unCentro.getDirector(), serviciosCGP, null);
		listaPOIS.add(unCGP);
	}

	public static List<Servicio> obtenerServiciosDeCGPExterno(CentroDTO unCentro) {
		List<Servicio> serviciosCGP = new ArrayList<Servicio>();
		unCentro.getServicios().forEach(servicio -> agregarAListaDeServicios(serviciosCGP, servicio));
		return serviciosCGP;
	}

	public static void agregarAListaDeServicios(List<Servicio> serviciosCGP, ServicioDTO unServicio) {
		String nombreDelServicio = unServicio.getNombre();
		HorarioYDia nuevoHorario = new HorarioYDia();
		unServicio.getHorariosDisponibles().forEach(unHorario -> agregarAHorarioDeServicio(nuevoHorario, unHorario));
		Servicio nuevoServicio = new Servicio(nombreDelServicio, nuevoHorario, null);
		serviciosCGP.add(nuevoServicio);

	}

	public static void agregarAHorarioDeServicio(HorarioYDia unHorarioYDia, RangoServicioDTO unHorario) {
		DayOfWeek dia = DayOfWeek.of(unHorario.getDiaSemana());
		LocalDateTime horarioInicio = LocalDateTime.now().withHour(unHorario.getHoraDesde()).withMinute(unHorario.getMinutoDesde());
		LocalDateTime horarioFin = LocalDateTime.now().withHour(unHorario.getHoraHasta()).withMinute(unHorario.getMinutoHasta());
		IntervaloHorario intervalo = new IntervaloHorario(horarioInicio, horarioFin);
		ArrayList<IntervaloHorario> listaDeIntervalos = new ArrayList<IntervaloHorario>();
		GestorIntervalos gestor = new GestorIntervalos(listaDeIntervalos);
		listaDeIntervalos.add(intervalo);
		HashMapeameEsta agenda = new HashMapeameEsta();
		agenda.put(dia, gestor);
		unHorarioYDia.setAgenda(agenda);

	}
}
