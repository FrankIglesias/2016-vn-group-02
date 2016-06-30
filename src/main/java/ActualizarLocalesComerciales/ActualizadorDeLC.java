package ActualizarLocalesComerciales;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;

import Repositorio.GestorDeProcesos;
import Repositorio.RepoPOIs;

public class ActualizadorDeLC extends TimerTask {

	RepoPOIs repo = RepoPOIs.getInstance();


	public void actualizarListaDeLC() {

		try {
			File archivo = new File(System.getProperty("user.dir")
					+ System.getProperty("file.separator")
					+ "LocalesComerciales.txt");
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			String linea;

			while ((linea = br.readLine()) != null) {
				parsearYAgregarLocal(linea, repo);
			}

		} catch (Exception e) {
			System.out
					.println("NO SE PUDO ABRIR EL ARCHIVO DE LOCALES COMERCIALES\n");
		}
	}

	private static void parsearYAgregarLocal(String linea, RepoPOIs repositorio) {
		String[] nombreYpalabras = linea.split(";");
		if (!repositorio.tieneUnLocalConNombre(nombreYpalabras[0]).isEmpty()) {
			String[] linea3 = nombreYpalabras[1].split(" ");
			ArrayList<String> palabrasClave = new ArrayList<String>(
					Arrays.asList(linea3));
			repositorio.actualizarLocal(nombreYpalabras[0], palabrasClave);
		}

	}

	public void run() {
		System.out.println("Por realizarse...");
		this.actualizarListaDeLC();
		System.out.println("Realizado Correctamente");
		GestorDeProcesos.sem.release();

	}
}
