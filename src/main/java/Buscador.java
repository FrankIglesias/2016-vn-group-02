import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
  
  
public class Buscador { 
	
static List<POI> puntosDeIntereses = new ArrayList<POI>();
static BaseDeDatos baseDeDatos = new BaseDeDatos();
static List<BancoImpostor> bancosImpostores = new ArrayList<BancoImpostor>();

public static List<POI> buscarSegunPalabraClave(String unaFrase, Terminal unTerminal)
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

public static List<BancoImpostor> buscarBancoSegunNombreYServicio(String nombreBanco, String unServicio)
{
	List<BancoImpostor> bancosFiltrados = new ArrayList<BancoImpostor>();
	bancosFiltrados = bancosImpostores.stream().filter(unBanco -> unBanco.getNombre() == nombreBanco && unBanco.realizasUnServicio(unServicio)).collect(Collectors.toList());
	return bancosFiltrados;
}
public static void setPuntosDeIntereses(List<POI> unaLista)
{
	Buscador.puntosDeIntereses = unaLista;
}
public static void setBancosImpostores(List<BancoImpostor> unosBancos)
{
	bancosImpostores = unosBancos;
}
public void agregarNuevosPoi(POI nuevoPOI){
	puntosDeIntereses.add(nuevoPOI);
}
public void sacarPoi(POI POIaSacar){
	puntosDeIntereses.remove(POIaSacar);
	
}

	



}
