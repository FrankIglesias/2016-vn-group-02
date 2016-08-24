package GobiernoDeLaCiudadExterno;

import java.util.ArrayList;

import DesignDreamTeamLocation.Geolocalizacion;
import TypePois.POI;

public class POIGCBAdapter extends POI {
	static ArrayList<String> list = new ArrayList<String>();
	public POIGCBAdapter(Geolocalizacion point, String palabraClave) {
		super(point, "random", list, null, null);
		this.addPalabrasClaves(palabraClave);
		// TODO Auto-generated constructor stub
	}

}
