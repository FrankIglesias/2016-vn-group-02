import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.List;

public class GlobalTestVariables {
	/*
	 * FERIADOS: 9 DE JULIO, 25 DE MAYO
	 * COLECTIVO: GEOLOCALIZACION: (-34.5730928, -58.511029), LINEA: 127, PALABRASCLAVE: "colectivo" "transporte" "publico" "rueditas" "127"
	 *	CGP: GEOLOCALIZACION: (-34.5730009,-58.5047724), COMUNA: 12 
	 * VENTADEVOS: HORARIO: BANCARIO, NOMBRE: "Tarjeta vos"
	 * BANCO: HORARIO: BANCARIO, NOMBRE: "Banco Rio" PALABRASCLAVE: "dinero" "cuenta" "banco" "banelco"
	 * LOCAL: NOMBRE: "lo de mari", GEOLOCALIZACION: (-34.5735632,-58.5105945),
	 * RUBRO: NOMBRE: "Ventas chetas", DISTANCIA: 3000 
	 */

	public static List<Feriado> crearEInicializarListaDeFeriados() {
		List<Feriado> feriados = new ArrayList<Feriado>();


		LocalTime hora1 = LocalTime.of(00, 00);
		LocalTime hora2 = LocalTime.of(03, 00);
		IntervaloHorario intervalo1 = new IntervaloHorario(hora1, hora2);
		Feriado feriado1 = new Feriado(07, 9, intervalo1);
		
		LocalTime hora3 = LocalTime.of(11, 0);
		LocalTime hora4 = LocalTime.of(19, 30);
		IntervaloHorario intervalo2 = new IntervaloHorario(hora3, hora4);
		Feriado feriado2 = new Feriado(04, 25, intervalo2);
		feriados.add(feriado1);
		feriados.add(feriado2);

		return feriados;

	}

	private HorarioYDia crearHorarioTipoBancario() {
		HorarioYDia horario = new HorarioYDia();
		IntervaloHorario intervalo = new IntervaloHorario(LocalTime.of(10, 00),
				LocalTime.of(15, 00));
		List<IntervaloHorario> listaDeIntervalos = new ArrayList<IntervaloHorario>();
		horario.agregarIntervalo(DayOfWeek.MONDAY, listaDeIntervalos);
		horario.agregarIntervalo(DayOfWeek.TUESDAY, listaDeIntervalos);
		horario.agregarIntervalo(DayOfWeek.WEDNESDAY, listaDeIntervalos);
		horario.agregarIntervalo(DayOfWeek.THURSDAY, listaDeIntervalos);
		horario.agregarIntervalo(DayOfWeek.FRIDAY, listaDeIntervalos);

		listaDeIntervalos.add(intervalo);

		return horario;
	}

	public static Banco crearUnBanco() {
		Domicilio domicilioBanco = new Domicilio("Bolivia",
				"El Gaucho y Estrella Federal", "6058", "PB", "", "1419");
		Localidad localidadBanco = new Localidad("Capital Federal",
				"Buenos Aires", "Argentina");
		Geolocalizacion geolocalizacionBanco = new Geolocalizacion(-34.5735632,
				-58.5105945, domicilioBanco, localidadBanco);
		Banco banco = new Banco(geolocalizacionBanco, "Banco Rio",
				new ArrayList<String>(), crearEInicializarListaDeFeriados());
		banco.addPalabraClave("dinero");
		banco.addPalabraClave("cuenta");
		banco.addPalabraClave("banco");
		banco.addPalabraClave("banelco");
		return banco;
	}

	public static Colectivo crearUnColectivo() {

		Domicilio domicilioColectivo = new Domicilio("Manuel Alvarez Prado",
				"Bolivia y Av. de Los Constituyentes", "2402-2600", "", "",
				"1419");
		Localidad localidadColectivo = new Localidad("Capital Federal",
				"Buenos Aires", "Argentina");
		Geolocalizacion geolocalizacionColectivo = new Geolocalizacion(
				-34.5730928, -58.511029, domicilioColectivo, localidadColectivo);
		Colectivo colectivo = new Colectivo(geolocalizacionColectivo,
				"Colectivo", new ArrayList<String>(), "127",
				crearEInicializarListaDeFeriados());
		colectivo.addPalabraClave("colectivo");
		colectivo.addPalabraClave("transporte");
		colectivo.addPalabraClave("publico");
		colectivo.addPalabraClave("rueditas");
		return colectivo;
	}

	/*
	 * domicilioPepe = new Domicilio("La Rioja", "San Juan y Humberto Primo",
	 * "1000", "", "", "1111"); localidadPepe = new Localidad("Capital Federal",
	 * "Buenos Aires", "Argentina");
	 * 
	 * geolocalizacionPersonaCercanaColectivo = new Geolocalizacion(-34.5730926,
	 * -58.512000, domicilioPepe, localidadPepe);
	 * geolocalizacionPersonaCercanaCGP = new Geolocalizacion(-34.5730926,
	 * -58.5010000, domicilioPepe, localidadPepe);
	 * geolocalizacionPersonaCercanaLocal = new Geolocalizacion(-34.5730926,
	 * -58.5002200, domicilioPepe,localidadPepe);
	 * geolocalizacionPersonaLejosDeTodo = new Geolocalizacion
	 * (-34.5739926,-58.5992200,domicilioPepe, localidadPepe);
	 */

	public CGP crearUnCGP() {
		Domicilio domicilioCGP = new Domicilio("Av. de los Constituyentes",
				"Jose Pascual Tamborini e Ibera", "5836", "PB", "", "1419");
		Localidad localidadCGP = new Localidad("Capital Federal",
				"Buenos Aires", "Argentina");
		Geolocalizacion geolocalizacionCGP = new Geolocalizacion(-34.5730009,
				-58.5047724, domicilioCGP, localidadCGP);
		Servicio ventaDeVOS = new Servicio("Tarjeta vos",
				crearHorarioTipoBancario());
		List<Servicio> serviciosDelCGP = new ArrayList<Servicio>();
		serviciosDelCGP.add(ventaDeVOS);
		CGP centroDeCGP = new CGP(geolocalizacionCGP, "Comuna 12",
				serviciosDelCGP, crearEInicializarListaDeFeriados());
		return centroDeCGP;
	}

	public Local crearUnLocal() {

		Rubro unRubro = new Rubro("Ventas chetas", 3000);
		Domicilio domicilioLocal = new Domicilio("Bolivia",
				"El Gaucho y Estrella Federal", "6058", "PB", "", "1419");
		Localidad localidadLocal = new Localidad("Capital Federal",
				"Buenos Aires", "Argentina");
		Geolocalizacion geolocalizacionLocal = new Geolocalizacion(-34.5735632,
				-58.5105945, domicilioLocal, localidadLocal);
		Local unLocal = new Local(geolocalizacionLocal, "lo de mari",
				crearHorarioTipoBancario(), unRubro,
				crearEInicializarListaDeFeriados());
		return unLocal;

	}

}
