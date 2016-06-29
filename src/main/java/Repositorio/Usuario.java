import DesignDreamTeamLocation.Geolocalizacion;

public class Usuario {
	
	Geolocalizacion point;
	
	public Usuario(Geolocalizacion point){
		this.point = point;
	}

	public int getComuna() {
		return point.getDomicilio().getComuna();
	}
	
	
	
}
