import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
  
  
public class Buscador { 
	

static List<POI> puntosDeIntereses = new ArrayList<POI>();
static BaseDeDatos baseDeDatos = new BaseDeDatos();


public List<POI> buscarSegunPalabraClave(String unaFrase, Terminal unTerminal)
{
	double inicio, fin;
	inicio = System.currentTimeMillis();
	List<POI> puntosSegunPalabra = new ArrayList<POI>();
	puntosSegunPalabra = puntosDeIntereses.stream().filter(unPunto -> unPunto.tenesUnaPalabraDe(unaFrase)).collect(Collectors.toList());
	fin = System.currentTimeMillis();
	baseDeDatos.addBusqueda(unTerminal, unaFrase, (fin - inicio));
	unTerminal.addResultadosParcialesAlReporte(puntosSegunPalabra);
	return puntosSegunPalabra;
}


public static void setPuntosDeIntereses(List<POI> unaLista)
{
	Buscador.puntosDeIntereses = unaLista;
}





}
