import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.experimental.theories.Theories;

public class ActualizadorDeLC {

	static String ruta;

	public void setRuta() {

		String nombreArchivo = "LocalesComerciales.txt";
		ruta = System.getProperty("user.dir") + "\\" + nombreArchivo;
	}

	public void actualizarListaDeLC(RepoLocalesComerciales repositorio) {

		try {
			this.setRuta();
			File archivo = new File(ruta);
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			String linea;

			while ((linea = br.readLine()) != null) {
				parsearYAgregarLocal(linea, repositorio);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void parsearYAgregarLocal(String linea, RepoLocalesComerciales repositorio) {
		String[] nombreYpalabras = linea.split(";");
		if (!repositorio.tieneUnLocalConNombre(nombreYpalabras[0]).isEmpty()) {
			String[] linea3 = nombreYpalabras[1].split(" ");
			ArrayList<String> palabrasClave = new ArrayList<String>(Arrays.asList(linea3));
			repositorio.actualizarLocal(nombreYpalabras[0], palabrasClave);
		}

	}
}
