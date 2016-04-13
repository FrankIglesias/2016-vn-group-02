import java.util.ArrayList;
import java.util.Arrays;
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
	

}