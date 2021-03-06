package tests;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import DesignDreamTeamLocation.Domicilio;
import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamLocation.Localidad;
import DesignDreamTeamTime.Feriado;
import DesignDreamTeamTime.GestorIntervalos;
import DesignDreamTeamTime.HorarioYDia;
import DesignDreamTeamTime.IntervaloHorario;
import TypePois.Banco;
import TypePois.CGP;
import TypePois.Colectivo;
import TypePois.Local;
import TypePois.Rubro;
import TypePois.Servicio;

public class GlobalTestVariables {

	public static List<Feriado> crearFeriado(int horaInicial, int horaFinal, int mes, int dia) {
		List<Feriado> feriados = new ArrayList<Feriado>();

		LocalDateTime hora1 = LocalDateTime.now().withHour(horaInicial).withMinute(00);
		LocalDateTime hora2 =  LocalDateTime.now().withHour(horaFinal).withMinute(00);
		IntervaloHorario intervalo1 = new IntervaloHorario(hora1, hora2);
		Feriado feriado1 = new Feriado(mes, dia, intervalo1);

		feriados.add(feriado1);

		return feriados;

	}
	
	public static List<Feriado> crearFeriadoVacio(){
		List<Feriado> feriados = new ArrayList<Feriado>();
		return feriados;
	}

	public static List<Feriado> crearFeriadoNoAbierto() {
		if (LocalTime.now().getHour() == 0 || LocalTime.now().getHour() == 1){
			return crearFeriado(3, 4,
					LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
		}
		else
		return crearFeriado(LocalTime.now().getHour() - 2, LocalTime.now().getHour() - 1,
				LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());

	}

	public static List<Feriado> crearFeriadoAbierto() {
		if (LocalTime.now().getHour() == 23){
			return crearFeriado(23, 00, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
		}
		return crearFeriado(LocalTime.now().getHour(), LocalTime.now().getHour() + 1, LocalDate.now().getMonthValue(),
				LocalDate.now().getDayOfMonth());
	}

	public static HorarioYDia crearUnHorarioDeLunesAViernes (LocalDateTime horaInicial, LocalDateTime horaFinal){
		HorarioYDia horario = new HorarioYDia();
		IntervaloHorario intervalo = new IntervaloHorario(horaInicial, horaFinal);
		ArrayList<IntervaloHorario> listaDeIntervalos = new ArrayList<IntervaloHorario>();
		GestorIntervalos gestorIntervalos = new GestorIntervalos(listaDeIntervalos);
		horario.agregarIntervalo(DayOfWeek.MONDAY, gestorIntervalos);
		horario.agregarIntervalo(DayOfWeek.TUESDAY, gestorIntervalos);
		horario.agregarIntervalo(DayOfWeek.WEDNESDAY, gestorIntervalos);
		horario.agregarIntervalo(DayOfWeek.THURSDAY, gestorIntervalos);
		horario.agregarIntervalo(DayOfWeek.FRIDAY, gestorIntervalos);

		listaDeIntervalos.add(intervalo);
		
		return horario;
		
	}

	public static Banco crearUnBanco(List<Feriado> feriados) {
		Domicilio domicilioBanco = new Domicilio("Bolivia", "El Gaucho y Estrella Federal", "6058", "PB", "", "1419", 0);
		Localidad localidadBanco = new Localidad("Capital Federal", "Buenos Aires", "Argentina");
		Geolocalizacion geolocalizacionBanco = new Geolocalizacion(-34.5735632, -58.5105945, domicilioBanco,
				localidadBanco);
		Banco banco = new Banco(geolocalizacionBanco, "Banco Rio", new ArrayList<String>(),
				feriados);
		banco.addPalabrasClaves("dinero");
		banco.addPalabrasClaves("cuenta");
		banco.addPalabrasClaves("banco");
		banco.addPalabrasClaves("banelco");
		banco.addPalabrasClaves("depositos");
		return banco;
	}

	public static Colectivo crearUnColectivo() {

		Domicilio domicilioColectivo = new Domicilio("Manuel Alvarez Prado", "Bolivia y Av. de Los Constituyentes",
				"2402-2600", "", "", "1419", 0);
		Localidad localidadColectivo = new Localidad("Capital Federal", "Buenos Aires", "Argentina");
		Geolocalizacion geolocalizacionColectivo = new Geolocalizacion(-34.5730928, -58.511029, domicilioColectivo,
				localidadColectivo);
		Colectivo colectivo = new Colectivo(geolocalizacionColectivo, "Colectivo", new ArrayList<String>(), "127",
				crearFeriadoVacio());
		colectivo.addPalabrasClaves("colectivo");
		colectivo.addPalabrasClaves("transporte");
		colectivo.addPalabrasClaves("publico");
		colectivo.addPalabrasClaves("rueditas");
		return colectivo;
	}

	public static CGP crearUnCGP(List<Feriado> feriados) {
		Domicilio domicilioCGP = new Domicilio("Junin", "Jose Pascual Tamborini e Ibera", "5836",
				"PB", "", "1419", 0);
		Localidad localidadCGP = new Localidad("Capital Federal", "Buenos Aires", "Argentina");
		Geolocalizacion geolocalizacionCGP = new Geolocalizacion(-34.5730009, -58.5047724, domicilioCGP, localidadCGP);
		Servicio ventaDeVOS = new Servicio("Tarjeta vos", crearUnHorarioDeLunesAViernes(LocalDateTime.now().withHour(10).withMinute(00), LocalDateTime.now().withHour(15).withMinute(00)), feriados);
		List<Servicio> serviciosDelCGP = new ArrayList<Servicio>();
		serviciosDelCGP.add(ventaDeVOS);
		CGP centroDeCGP = new CGP(geolocalizacionCGP, "Comuna 12", serviciosDelCGP, feriados);
		return centroDeCGP;
	}
	
	public static CGP crearOtroCGP(List<Feriado> feriados)
	{
		Domicilio domicilioCGP = new Domicilio("Av. Rivadavia 7202", "Jose Pascual Tamborini e Ibera", "5836",
				"PB", "", "1419", 0);
		Localidad localidadCGP = new Localidad("Capital Federal", "Buenos Aires", "Argentina");
		Geolocalizacion geolocalizacionCGP = new Geolocalizacion(-34.5730009, -58.5047724, domicilioCGP, localidadCGP);
		Servicio ventaDeVOS = new Servicio("Tarjeta vos", crearUnHorarioDeLunesAViernes(LocalDateTime.now().withHour(10).withMinute(00), LocalDateTime.now().withHour(15).withMinute(00)), feriados);
		List<Servicio> serviciosDelCGP = new ArrayList<Servicio>();
		serviciosDelCGP.add(ventaDeVOS);
		CGP centroDeCGP = new CGP(geolocalizacionCGP, "Comuna 12", serviciosDelCGP, feriados);
		return centroDeCGP;
	}

	public static Local crearUnLocal(List<Feriado> feriados) {

		Rubro unRubro = new Rubro("Ventas chetas", 3000);
		Domicilio domicilioLocal = new Domicilio("Bolivia", "El Gaucho y Estrella Federal", "6058", "PB", "", "1419", 0);
		Localidad localidadLocal = new Localidad("Capital Federal", "Buenos Aires", "Argentina");
		Geolocalizacion geolocalizacionLocal = new Geolocalizacion(-34.5735632, -58.5105945, domicilioLocal,
				localidadLocal);
		Local unLocal = new Local(geolocalizacionLocal, "lo de mari", crearUnHorarioDeLunesAViernes(LocalDateTime.now().withHour(8).withMinute(00), LocalDateTime.now().withHour(17).withMinute(00)), unRubro,
				feriados);
		return unLocal;
		

	}

}
