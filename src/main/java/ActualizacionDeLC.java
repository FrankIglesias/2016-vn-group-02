import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class ActualizacionDeLC {
	static String ruta = "C:\\Users\\Mi Pc\\Desktop\\UTN\\2016-vn-group-02\\src\\main\\java\\LocalesComerciales.txt";

	public static void main(String[] args) {

	}

	public static void actualizarListaDeLC(RepoLocalesComerciales repositorio) {

		try {
			File archivo = new File(ruta);
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			String linea;

			while ((linea = br.readLine()) != null) {
				String[] nombreYpalabras = linea.split(";");
				if( repositorio.tieneUnLocalConNombre(nombreYpalabras[0]) != null){
					String[] linea3 = nombreYpalabras[1].split(" ");
					ArrayList<String> palabrasClave = new ArrayList<String>(Arrays.asList(linea3));
					repositorio.actualizarLocal(nombreYpalabras[0], palabrasClave);
				};
			}

		} catch (Exception e) {
			System.out.println("ERROR");
		}
	}
	
	
	public static void proximaLinea() {

	}
}
