import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.List;

public class GlobalTestVariables {
	/*
	 * FERIADOS: 9 DE JULIO, 25 DE MAYO COLECTIVO: GEOLOCALIZACION:
	 * (-34.5730928, -58.511029), LINEA: 127, PALABRASCLAVE: "colectivo"
	 * "transporte" "publico" "rueditas" "127" CGP: GEOLOCALIZACION:
	 * (-34.5730009,-58.5047724), COMUNA: 12 VENTADEVOS: HORARIO: BANCARIO,
	 * NOMBRE: "Tarjeta vos" BANCO: HORARIO: BANCARIO, NOMBRE: "Banco Rio"
	 * PALABRASCLAVE: "dinero" "cuenta" "banco" "banelco" LOCAL: NOMBRE:
	 * "lo de mari", GEOLOCALIZACION: (-34.5735632,-58.5105945), RUBRO: NOMBRE:
	 * "Ventas chetas", DISTANCIA: 3000
	 */

	public static List<Feriado> crearFeriado(int horaInicial, int horaFinal, int mes, int dia) {
		List<Feriado> feriados = new ArrayList<Feriado>();

		LocalTime hora1 = LocalTime.of(horaInicial, 00);
		LocalTime hora2 = LocalTime.of(horaFinal, 00);
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
		return crearFeriado(LocalTime.now().getHour() - 2, LocalTime.now().getHour() - 1,
				LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());

	}

	public static List<Feriado> crearFeriadoAbierto() {
		return crearFeriado(LocalTime.now().getHour() - 1, LocalTime.now().getHour() + 1, LocalDate.now().getMonthValue(),
				LocalDate.now().getDayOfMonth());
	}

	public static HorarioYDia crearUnHorarioDeLunesAViernes (LocalTime horaInicial, LocalTime horaFinal){
		HorarioYDia horario = new HorarioYDia();
		IntervaloHorario intervalo = new IntervaloHorario(horaInicial, horaFinal);
		List<IntervaloHorario> listaDeIntervalos = new ArrayList<IntervaloHorario>();
		horario.agregarIntervalo(DayOfWeek.MONDAY, listaDeIntervalos);
		horario.agregarIntervalo(DayOfWeek.TUESDAY, listaDeIntervalos);
		horario.agregarIntervalo(DayOfWeek.WEDNESDAY, listaDeIntervalos);
		horario.agregarIntervalo(DayOfWeek.THURSDAY, listaDeIntervalos);
		horario.agregarIntervalo(DayOfWeek.FRIDAY, listaDeIntervalos);

		listaDeIntervalos.add(intervalo);
		
		return horario;
		
	}

	public static Banco crearUnBanco(List<Feriado> feriados) {
		Domicilio domicilioBanco = new Domicilio("Bolivia", "El Gaucho y Estrella Federal", "6058", "PB", "", "1419");
		Localidad localidadBanco = new Localidad("Capital Federal", "Buenos Aires", "Argentina");
		Geolocalizacion geolocalizacionBanco = new Geolocalizacion(-34.5735632, -58.5105945, domicilioBanco,
				localidadBanco);
		Banco banco = new Banco(geolocalizacionBanco, "Banco Rio", new ArrayList<String>(),
				feriados);
		banco.addPalabraClave("dinero");
		banco.addPalabraClave("cuenta");
		banco.addPalabraClave("banco");
		banco.addPalabraClave("banelco");
		return banco;
	}

	public static Colectivo crearUnColectivo() {

		Domicilio domicilioColectivo = new Domicilio("Manuel Alvarez Prado", "Bolivia y Av. de Los Constituyentes",
				"2402-2600", "", "", "1419");
		Localidad localidadColectivo = new Localidad("Capital Federal", "Buenos Aires", "Argentina");
		Geolocalizacion geolocalizacionColectivo = new Geolocalizacion(-34.5730928, -58.511029, domicilioColectivo,
				localidadColectivo);
		Colectivo colectivo = new Colectivo(geolocalizacionColectivo, "Colectivo", new ArrayList<String>(), "127",
				crearFeriadoVacio());
		colectivo.addPalabraClave("colectivo");
		colectivo.addPalabraClave("transporte");
		colectivo.addPalabraClave("publico");
		colectivo.addPalabraClave("rueditas");
		return colectivo;
	}

	public static CGP crearUnCGP(List<Feriado> feriados) {
		Domicilio domicilioCGP = new Domicilio("Av. de los Constituyentes", "Jose Pascual Tamborini e Ibera", "5836",
				"PB", "", "1419");
		Localidad localidadCGP = new Localidad("Capital Federal", "Buenos Aires", "Argentina");
		Geolocalizacion geolocalizacionCGP = new Geolocalizacion(-34.5730009, -58.5047724, domicilioCGP, localidadCGP);
		Servicio ventaDeVOS = new Servicio("Tarjeta vos", crearUnHorarioDeLunesAViernes(LocalTime.of(10, 00), LocalTime.of(15, 00)));
		List<Servicio> serviciosDelCGP = new ArrayList<Servicio>();
		serviciosDelCGP.add(ventaDeVOS);
		CGP centroDeCGP = new CGP(geolocalizacionCGP, "Comuna 12", serviciosDelCGP, feriados);
		return centroDeCGP;
	}

	public static Local crearUnLocal(List<Feriado> feriados) {

		Rubro unRubro = new Rubro("Ventas chetas", 3000);
		Domicilio domicilioLocal = new Domicilio("Bolivia", "El Gaucho y Estrella Federal", "6058", "PB", "", "1419");
		Localidad localidadLocal = new Localidad("Capital Federal", "Buenos Aires", "Argentina");
		Geolocalizacion geolocalizacionLocal = new Geolocalizacion(-34.5735632, -58.5105945, domicilioLocal,
				localidadLocal);
		Local unLocal = new Local(geolocalizacionLocal, "lo de mari", crearUnHorarioDeLunesAViernes(LocalTime.of(8, 00), LocalTime.of(17, 00)), unRubro,
				feriados);
		return unLocal;

	}

}
