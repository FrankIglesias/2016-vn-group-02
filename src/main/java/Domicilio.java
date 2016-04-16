
public class Domicilio {

	private String callePrincipal;
	private String entreCalles;
	private int altura;
	private int piso;
	private int unidad;
	private int codigoPostal;

	public Domicilio(String calle_Principal, String entre_Calles, int altura_fija, int piso_fijo, int unidad_delPiso, int codigo_postal) {
		callePrincipal = calle_Principal;
		entreCalles = entre_Calles;
		altura = altura_fija;
		piso = piso_fijo;
		unidad = unidad_delPiso;
		codigoPostal = codigo_postal;

	}
}
