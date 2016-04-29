import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
  
  
public class Buscador { 
	
static List<POI> puntosDeIntereses = new ArrayList<POI>();

public static List<POI> buscarSegunPalabraClave(String unaFrase)
{
	List<POI> puntosSegunPalabra = new ArrayList<POI>();
	puntosSegunPalabra = puntosDeIntereses.stream().filter(unPunto -> unPunto.tenesUnaPalabraDe(unaFrase)).collect(Collectors.toList());
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