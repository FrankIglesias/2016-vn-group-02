import CGPExterno.CentroDTO;
import CGPExterno.RangoServicioDTO;
import CGPExterno.RepositorioCGPExterno;
import CGPExterno.ServicioDTO;
import DesignDreamTeamLocation.Domicilio;
import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamTime.HorarioYDia;
import DesignDreamTeamTime.IntervaloHorario;
import TypePois.CGP;
import TypePois.POI;
import TypePois.Servicio;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class RepositorioCGPExternoAdapter {
	public static ArrayList<POI> obtenerCGPDesdeRepositorioExterno(String criterio) {
		ArrayList<POI> pois = new ArrayList<POI>();
		RepositorioCGPExterno repositorio = new RepositorioCGPExterno();
		List<CentroDTO> listaCentros = repositorio.buscarCGPs(criterio);
		listaCentros.forEach(unCentro -> agregarAListaDeCGP(pois, unCentro));
		return pois;

	}

	public static void agregarAListaDeCGP(ArrayList<POI> listaPOIS, CentroDTO unCentro) {
		String[] domicilioDeCentro = unCentro.getDomicilio().split(" ");
		String calleDeDomicilioCentro;
		String alturaDeDomicilioCentro;

		calleDeDomicilioCentro = domicilioDeCentro[0];
		alturaDeDomicilioCentro = domicilioDeCentro[1];

		/*
		 * int tamanio = domicilioDeCentro.length; for(int i=0; i<tamanio; i++)
		 * { calleDeDomicilioCentro = calleDeDomicilioCentro +
		 * domicilioDeCentro[i]; } alturaDeDomicilioCentro =
		 * domicilioDeCentro[tamanio];
		 */
		// if(domicilioDeCentro.length == 3)
		// {
		// calleDeDomicilioCentro = domicilioDeCentro[0] + domicilioDeCentro[1];
		// alturaDeDomicilioCentro = domicilioDeCentro[2];

		// }
		/*
		 * else { calleDeDomicilioCentro = domicilioDeCentro[0];
		 * alturaDeDomicilioCentro = domicilioDeCentro[1]; }
		 */

		Domicilio domicilioCGP = new Domicilio(calleDeDomicilioCentro, null, alturaDeDomicilioCentro, null, null, null);
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
		LocalTime horarioInicio = LocalTime.of(unHorario.getHoraDesde(), unHorario.getMinutoDesde());
		LocalTime horarioFin = LocalTime.of(unHorario.getHoraHasta(), unHorario.getMinutoHasta());
		IntervaloHorario intervalo = new IntervaloHorario(horarioInicio, horarioFin);
		List<IntervaloHorario> listaDeIntervalos = new ArrayList<IntervaloHorario>();
		listaDeIntervalos.add(intervalo);
		Map<DayOfWeek, List<IntervaloHorario>> agenda = new HashMap<DayOfWeek, List<IntervaloHorario>>();
		agenda.put(dia, listaDeIntervalos);
		unHorarioYDia.setAgenda(agenda);

	}
}
