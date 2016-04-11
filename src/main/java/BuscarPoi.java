import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BuscarPoi {
 private static  int localizacion;
private  static List<POI> puntosDeInteres = new ArrayList<POI>();
 
 public static List<POI> buscarSegunPalabraClave(String unaPalabra) {
		
		List<POI> puntosSegunPalabra = new ArrayList<POI>();
		puntosSegunPalabra = puntosDeInteres.stream().filter(unPunto -> unPunto.tenesUnaPalabra(unaPalabra))
				.collect(Collectors.toList());
		return puntosSegunPalabra;

	}

}
