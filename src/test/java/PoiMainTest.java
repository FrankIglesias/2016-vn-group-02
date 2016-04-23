

import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;

public class PoiMainTest {
	protected Domicilio domicilioBanco;
	protected Localidad localidadBanco;
	protected Geolocalizacion geolocalizacionBanco;
	protected Banco banco;
	protected Domicilio domicilioColectivo;
	protected Localidad localidadColectivo;
	protected Geolocalizacion geolocalizacionColectivo;
	protected Colectivo colectivo;
	protected Domicilio domicilioCGP;
	protected Localidad localidadCGP;
	protected Geolocalizacion geolocalizacionCGP;
	private List<IntervaloHorario> intervaloFijo = new ArrayList<IntervaloHorario>();
	protected IntervaloHorario intervalo;
	protected List<Servicio> serviciosDelCGP = new ArrayList<Servicio>();
	protected Horario horarioPrueba;
	protected ArrayList<Horario> horario = new ArrayList<Horario>();
	protected Servicio ventaDeVOS;
	protected CGP centroDeCGP;
	protected Local unLocal;
	protected Rubro unRubro;
	protected LocalDateTime fechaAbierto;
	protected LocalDateTime fechaCerrado;
	protected LocalDate fecha1;
	protected LocalTime hora1;
	protected LocalDate fecha2;
	protected LocalTime hora2;
	protected List<Feriado> feriados = new ArrayList<Feriado>();
	protected Colectivo unColectivo;
	protected Feriado feriado1;
	protected Feriado feriado2;
	protected IntervaloHorario intervalo1;
	protected IntervaloHorario intervalo2;
	protected LocalTime hora3;
	protected LocalTime hora4;
	protected LocalDate diaActual;
	protected LocalDateTime horarioActual;
	
	@Before 
	public void init(){  
	
		domicilioBanco 			= new Domicilio("Bolivia","El Gaucho y Estrella Federal","6058","PB","","1419");
		localidadBanco 			= new Localidad("Capital Federal", "Buenos Aires", "Argentina");
		geolocalizacionBanco 	= new Geolocalizacion(-34.5735632, -58.5105945, domicilioBanco, localidadBanco);
		banco = new Banco(geolocalizacionBanco, "Banco Rio", new ArrayList<String>(), feriados);
		
		domicilioColectivo 			= new Domicilio("Manuel Alvarez Prado","Bolivia y Av. de Los Constituyentes","2402-2600","","","1419");
		localidadColectivo 			= new Localidad("Capital Federal", "Buenos Aires", "Argentina");
		geolocalizacionColectivo 	= new Geolocalizacion(-34.5730928, -58.511029, domicilioColectivo, localidadColectivo);
		colectivo = new Colectivo(geolocalizacionColectivo, "Colectivo", new ArrayList<String>(),"127", feriados);
		
		domicilioCGP 			= new Domicilio("Av. de los Constituyentes","Jose Pascual Tamborini e Ibera","5836","PB","","1419");
		localidadCGP 			= new Localidad("Capital Federal", "Buenos Aires", "Argentina");
		geolocalizacionCGP 		= new Geolocalizacion(-34.5730009, -58.5047724, domicilioColectivo, localidadColectivo);
		intervalo = new IntervaloHorario(LocalTime.of(10, 00), LocalTime.of(15, 00));
		Horario lunes = new Horario(DayOfWeek.MONDAY, intervaloFijo);
		Horario martes = new Horario(DayOfWeek.TUESDAY, intervaloFijo);
		Horario miercoles = new Horario(DayOfWeek.WEDNESDAY, intervaloFijo);
		Horario jueves = new Horario(DayOfWeek.THURSDAY, intervaloFijo);
		Horario viernes = new Horario(DayOfWeek.FRIDAY, intervaloFijo);
		intervaloFijo.add(intervalo);
		horario.add(lunes);
		horario.add(martes);
		horario.add(miercoles);
		horario.add(jueves);
		horario.add(viernes);
		ventaDeVOS = new Servicio("Tarjeta vos", horario);
		serviciosDelCGP.add(ventaDeVOS);
		centroDeCGP = new CGP(geolocalizacionCGP, "Comuna 12", serviciosDelCGP, feriados);

		unRubro = new Rubro("Ventas chetas", 3000);
		unLocal = new Local(geolocalizacionBanco, "lo de mari", horario, unRubro, feriados);
		
		
		
		
	}
}
