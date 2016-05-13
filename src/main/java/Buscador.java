import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
  
  
public class Buscador { 
	
static List<POI> puntosDeIntereses = new ArrayList<POI>();
static Notificador notificador = new Notificador();

public static List<POI> buscarSegunPalabraClave(String unaFrase)
{
	double inicio, fin;
	inicio = System.currentTimeMillis();
	List<POI> puntosSegunPalabra = new ArrayList<POI>();
	puntosSegunPalabra = puntosDeIntereses.stream().filter(unPunto -> unPunto.tenesUnaPalabraDe(unaFrase)).collect(Collectors.toList());
	fin = System.currentTimeMillis();
	notificador.addBusqueda("unTerminal", unaFrase, (fin - inicio));
	return puntosSegunPalabra;
}

public static void setPuntosDeIntereses(List<POI> unaLista)
{
	Buscador.puntosDeIntereses = unaLista;
}
	
public Buscador()
{ 
	super();
}
	



}