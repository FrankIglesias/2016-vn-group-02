package src.main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;


public class Sistema {
	
static List<POI> puntosDeIntereses = new ArrayList<POI>();

//public static void main(String[] args) {}


public static List<POI> buscarSegunPalabraClave(String unaFrase)
{

	
	List<POI> puntosSegunPalabra = new ArrayList<POI>();
	
	
	puntosSegunPalabra = puntosDeIntereses.stream().filter(unPunto -> unPunto.tenesUnaPalabraDe(unaFrase)).collect(Collectors.toList());
	return puntosSegunPalabra;
}

public static void setList(List<POI> unaLista)
{
	Sistema.puntosDeIntereses = unaLista;
}
	
public Sistema()
{ 
	super();
}
public static void main (String [ ] args) {
	GregorianCalendar fechaActual = new  GregorianCalendar(2014, 04, 13, 14, 25);
	System.out.println(fechaActual.get(Calendar.HOUR_OF_DAY));
	System.out.println(fechaActual.get(Calendar.DAY_OF_WEEK));
  
  }
	



}