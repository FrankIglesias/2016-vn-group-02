package ActualizarLocalesComerciales;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import DesignDreamTeamErrorHandlers.DDTErrorHandler;
import DesignDreamTeamProcesses.DesignDreamTeamProcess;
import Repositorios.RepoPOIs;


public class ActualizadorDeLC extends DesignDreamTeamProcess {

	private RepoPOIs repo = RepoPOIs.getInstance();
	private String archivoALevantar;
	private DDTErrorHandler accionDeError;
	private static ArrayList<ArrayList<String>> listaDeListasDePalabrasClaves = new ArrayList<ArrayList<String>>();

	public void setArchivoALevantar(String unArchivo) {
		this.archivoALevantar = unArchivo;
	}

	public void actualizarListaDeLC() {

		try {
			File archivo = new File(
					System.getProperty("user.dir") + System.getProperty("file.separator") + archivoALevantar);
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				parsearYAgregarLocal(linea, repo);
			}
			br.close();
		} catch (Exception e) {
			System.out.println("NO SE PUDO ABRIR EL ARCHIVO DE LOCALES COMERCIALES\n");

		}
	}

	public void setAccionDeError(DDTErrorHandler accionDeError) {
		this.accionDeError = accionDeError;
	}

	public static ArrayList<ArrayList<String>> getLista() {
		return listaDeListasDePalabrasClaves;
	}

	public ArrayList<String> obtenerPalabrasClavesDePOIDeLinea(String unaLinea) {
		String[] nombreYPalabras = unaLinea.split(";");
		String[] linea3 = nombreYPalabras[1].split(" ");
		ArrayList<String> palabrasClaves = new ArrayList<String>(Arrays.asList(linea3));
		return palabrasClaves;
	}

	private static void parsearYAgregarLocal(String linea, RepoPOIs repositorio) {
		String[] nombreYpalabras = linea.split(";");
		if (!repositorio.tieneUnLocalConNombre(nombreYpalabras[0]).isEmpty()) {
			String[] linea3 = nombreYpalabras[1].split(" ");
			ArrayList<String> palabrasClave = new ArrayList<String>(Arrays.asList(linea3));
			repositorio.actualizarLocal(nombreYpalabras[0], palabrasClave);
			ActualizadorDeLC.agregarListaPalabrasClaves(palabrasClave);
		}

	}

	public static void agregarListaPalabrasClaves(ArrayList<String> palabrasClaves) {
		listaDeListasDePalabrasClaves.add(palabrasClaves);
	}

	public void run() throws JobExecutionException {
		try {
			this.actualizarListaDeLC();
		} catch (RuntimeException e) {
			accionDeError.ejecutarAccion(this, e);
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		this.actualizarListaDeLC();
		
	}
}
