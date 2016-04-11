import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Sistema {
	
static List<POI> puntosDeInteres = new ArrayList<POI>();

//public static void main(String[] args) {}


public static List<POI> buscarSegunPalabraClave(String unaPalabra)
{
	List<POI> puntosSegunPalabra = new ArrayList<POI>();
	puntosSegunPalabra = puntosDeInteres.stream().filter(unPunto -> unPunto.tenesUnaPalabra(unaPalabra)).collect(Collectors.toList());
	return puntosSegunPalabra;
}

public static void setList(List<POI> unaLista)
{
	Sistema.puntosDeInteres = unaLista;
}
	
public Sistema()
{ 
	super();
}
	

}