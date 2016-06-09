import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import TypePois.POI;

public class Buscador { 
	

static List<POI> puntosDeIntereses = new ArrayList<POI>();
static BaseDeDatos baseDeDatos = BaseDeDatos.getInstance();
static double tiempoMax = 0.0001;


public List<POI> buscarSegunPalabraClave(String unaFrase, Terminal unTerminal)
{
	double inicio, fin;
	inicio = System.currentTimeMillis();
	List<POI> puntosSegunPalabra = puntosDeIntereses.stream().filter(unPunto -> unPunto.tenesUnaPalabraDe(unaFrase)).collect(Collectors.toList());
	fin = System.currentTimeMillis();
	baseDeDatos.addBusqueda(unTerminal, unaFrase, (fin - inicio), tiempoMax);
	unTerminal.addResultadosParcialesAlReporte(puntosSegunPalabra);
	return puntosSegunPalabra;
}

public void setTiempoMaximoDeBusqueda (double tiempoMax){
		Buscador.tiempoMax = tiempoMax;
}


public static void setPuntosDeIntereses(List<POI> unaLista)
{
	Buscador.puntosDeIntereses = unaLista;
}

public BaseDeDatos getBaseDeDatos(){
	return baseDeDatos;
}


}
