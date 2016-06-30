package TypePois;

import java.util.ArrayList;

import DesignDreamTeamLocation.Geolocalizacion;

public class genericPOI extends POI {
	static ArrayList<String> list = new ArrayList<String>();
	public genericPOI(Geolocalizacion point, String palabraClave) {
		super(point, "random", list, null, null);
		this.addPalabrasClaves(palabraClave);
		// TODO Auto-generated constructor stub
	}

}
