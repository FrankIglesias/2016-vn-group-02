import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;


public class TestDeDisponibilidadDePOISegunFeriado extends PoiMainTest{

	public Colectivo unColectivo;
	public List<Feriado> feriados = new ArrayList<Feriado>();
	public Feriado feriado1;
	public Feriado feriado2; 
	public IntervaloHorario intervalo1;
	public IntervaloHorario intervalo2;
	public LocalTime hora1;
	public LocalTime hora2;
	public LocalTime hora3;
	public LocalTime hora4;
	public LocalDate diaActual;
	public LocalDateTime horarioActual;
	
	@Before
	public void init(){ 
	hora1 = LocalTime.of(10,00);
	hora2 =  LocalTime.of(15,00);
	intervalo1 = new IntervaloHorario(hora1, hora2);	
	feriado1 = new Feriado(07, 9, intervalo1);
	hora3 = LocalTime.of(11, 0);
	hora4 = LocalTime.of(19,30);
	intervalo2 = new IntervaloHorario(hora3, hora4);
	feriado2 = new Feriado(04,26, intervalo2);
	unColectivo = new Colectivo(geolocalizacionColectivo, "linea2", new ArrayList<String>(), "152", feriados);
	unColectivo.addFeriado(feriado1);
	unColectivo.addFeriado(feriado2);
	
	}
	
	@Test
	public void ColectivoEstaEnFuncionamientoLosFeriados()
	{
		Assert.assertTrue(unColectivo.estaDisponible(LocalDateTime.now()));
		
	}
}
