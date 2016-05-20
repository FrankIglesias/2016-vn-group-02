import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class EncuentraSegunNombreYServicioTest extends GlobalTestVariables{

public BancoImpostor unBanco;
String servicioABuscar;
String nombreDeBancoABuscar;
Buscador buscador;
List<Servicio> serviciosBanco;
Servicio unServicio;
Servicio otroServicio;
List<Feriado> feriados;
HorarioYDia horarioYDia;


@Before
public void init() {
	horarioYDia = new HorarioYDia();
	horarioYDia = crearUnHorarioDeLunesAViernes(LocalTime.of(10, 20), LocalTime.of(23, 22));
	 feriados = new ArrayList<Feriado>();
	 feriados = crearFeriado(10,  20,  10,  10);
	unServicio = new Servicio("cobrar", horarioYDia, feriados);
	otroServicio = new Servicio("depositar", horarioYDia, feriados);
	serviciosBanco = new ArrayList<Servicio>();
	serviciosBanco.add(unServicio);
	serviciosBanco.add(otroServicio);
	buscador = new Buscador();
	unBanco = new BancoImpostor("frances", serviciosBanco);
}
@Test
public void encontrarBancoSegunNombreYServicio() {

	Assert.assertTrue(Buscador.buscarBancoSegunNombreYServicio("frances", "depositar").isEmpty());
}
	
}
