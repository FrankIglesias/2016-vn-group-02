package ActualizarLocalesComerciales;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;

import DesignDreamTeamErrors.ErrorHandler;
import DesignDreamTeamProcesses.GestorDeProcesos;
import DesignDreamTeamProcesses.DesignDreamTeamProcess;
import Repositorio.RepoPOIs;

public class ActualizadorDeLC extends DesignDreamTeamProcess {

	private RepoPOIs repo = RepoPOIs.getInstance();
	private String archivoALevantar;
	private ErrorHandler AccionDeError;

	public ActualizadorDeLC(ErrorHandler accion, Date date) {
		super(accion, date);
	}

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

		} catch (Exception e) {
			System.out.println("NO SE PUDO ABRIR EL ARCHIVO DE LOCALES COMERCIALES\n");
			throw new RuntimeException();
		}
	}

	public void setAccionDeError(ErrorHandler accionDeError) {
		AccionDeError = accionDeError;
	}

	public ArrayList<String> getPalabrasClavesDeLinea(String unPOI) {

		ArrayList<String> palabrasClaves = null;
		try {
			File archivo = new File(
					System.getProperty("user.dir") + System.getProperty("file.separator") + archivoALevantar);
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				if (unPOI == this.obtenerNombreDePOIDeLinea(linea))
					;
				{
					palabrasClaves = this.obtenerPalabrasClavesDePOIDeLinea(linea);
				}
			}
			return palabrasClaves;
		} catch (Exception e) {
			System.out.println("NO SE PUDO ABRIR EL ARCHIVO DE LOCALES COMERCIALES\n");

		}
		return palabrasClaves;
	}

	public String obtenerNombreDePOIDeLinea(String unaLinea) {
		String[] nombreYPalabras = unaLinea.split(";");
		return nombreYPalabras[0];

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
		}

	}

	public void run() {
		try {
			System.out.println("Realizando la actualizacion de locales...");
			this.actualizarListaDeLC();
			System.out.println("Actualizacion realizada correctamente");
		} catch (RuntimeException e) {
			AccionDeError.ejecutarAccion(new Date(), this);
		}
		SemVamoASincronizarno_signal();
	}

	private void SemVamoASincronizarno_signal() {
		GestorDeProcesos.sem.release();
	}

}
