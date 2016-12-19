package Repositorios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import TypePois.POI;

public class Buscador {

	static List<POI> puntosDeIntereses = new ArrayList<POI>();
	static RepoDeBusquedas baseDeDatos = RepoDeBusquedas.getInstance();
	static RepoPOIs baseDeDatosDePois = RepoPOIs.getInstance();
	static double tiempoMax = 0.0001;
	
	public List<POI> buscarPoisMongo(String unaFrase, Terminal unTerminal) {
		double inicio, fin;
		inicio = System.currentTimeMillis();
		String[] linea = unaFrase.split(" ");
		ArrayList<String> palabrasClave = new ArrayList<String>(Arrays.asList(linea));
		List<POI> puntosSegunPalabra = new ArrayList<POI>();
		palabrasClave.stream().forEach(palabrita -> puntosSegunPalabra
				.addAll(baseDeDatosDePois.obtenerDeMongoSegunPalabrasClave(palabrita)));
		puntosSegunPalabra.stream().forEach(unPunto -> unPunto.setUltimaFechaDeBusqueda());
		fin = System.currentTimeMillis();
		baseDeDatos.addBusqueda(unTerminal, unaFrase, (fin - inicio), tiempoMax, puntosSegunPalabra);
		unTerminal.addResultadosParcialesAlReporte(puntosSegunPalabra);
		return puntosSegunPalabra;
	}

	public List<POI> buscarPoisHibernate(String unaFrase, Terminal unTerminal) {
		double inicio, fin;
		inicio = System.currentTimeMillis();
		String[] linea = unaFrase.split(" ");
		ArrayList<String> palabrasClave = new ArrayList<String>(Arrays.asList(linea));
		List<POI> puntosSegunPalabra = new ArrayList<POI>();
		palabrasClave.stream().forEach(palabrita -> puntosSegunPalabra
				.addAll(baseDeDatosDePois.obtenerDeHibernateSegunPalabrasClave(palabrita)));
		puntosSegunPalabra.stream().forEach(unPunto -> unPunto.setUltimaFechaDeBusqueda());
		fin = System.currentTimeMillis();
		baseDeDatos.addBusqueda(unTerminal, unaFrase, (fin - inicio), tiempoMax, puntosSegunPalabra);
		unTerminal.addResultadosParcialesAlReporte(puntosSegunPalabra);
		return puntosSegunPalabra;

	}

	public void setTiempoMaximoDeBusqueda(double tiempoMax) {
		Buscador.tiempoMax = tiempoMax;
	}

	public static void setPuntosDeIntereses(List<POI> unaLista) {
		Buscador.puntosDeIntereses = unaLista;
	}

	public RepoDeBusquedas getBaseDeDatos() {
		return baseDeDatos;
	}

}
