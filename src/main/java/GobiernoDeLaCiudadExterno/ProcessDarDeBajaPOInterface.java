package GobiernoDeLaCiudadExterno;

import java.time.LocalDateTime;
import java.util.Map;

import DesignDreamTeamErrors.ErrorHandler;
import DesignDreamTeamLocation.Geolocalizacion;
import TypePois.POI;

public interface ProcessDarDeBajaPOInterface {
	public Map<Geolocalizacion, LocalDateTime> procesarPedido(String noProcesado);
	 
	public POI getPOI(Geolocalizacion geo);

	public void eliminarPOIs();
}
