package Controllers;

import java.util.List;

import AsignarAccionesUsuario.Accion;
import DesignDreamTeamLocation.Domicilio;
import DesignDreamTeamLocation.Geolocalizacion;
import DesignDreamTeamLocation.Localidad;
import Repositorios.RepoTerminales;
import Repositorios.Terminal;

public class ControllerRepoTerminales {

	private static ControllerRepoTerminales instancia = null;
	public RepoTerminales modeloTerminales = RepoTerminales.getInstance();

	public static ControllerRepoTerminales getInstance() {
		if (instancia == null) {
			instancia = new ControllerRepoTerminales();
		}
		return instancia;
	}

	public List<Terminal> listarTerminales(String nombre, int comuna) {
		return modeloTerminales.obtenerTerminales(nombre, comuna);
	}

	public void eliminarUnaTerminal(Terminal unaTerminal) {
		modeloTerminales.eliminarUnaTerminal(unaTerminal);
	}

	public Terminal agregarUnaTerminal(String nombre, int comuna, String latitud, String longitud) {
		Terminal terminal = new Terminal(nombre, comuna);
		terminal.getPoint().setLatitud(Double.parseDouble(latitud));
		terminal.getPoint().setLongitud(Double.parseDouble(longitud));
		modeloTerminales.persistirTerminal(terminal);
		return terminal;
	}

	public void editarUnaTerminal(String nombre, int comuna, String callePrincipal, String entreCalles, String altura,
			String piso, String unidad, String codigoPostal, String unaCiudad, String unaProvincia, String unPais,
			double latitud, double longitud) {

		Terminal unaTerminal = modeloTerminales.buscameUnaTerminal(nombre);
		eliminarUnaTerminal(unaTerminal);

		Domicilio unDomi = new Domicilio(callePrincipal, entreCalles, altura, piso, unidad, codigoPostal, comuna);
		Localidad unaLoca = new Localidad(unaCiudad, unaProvincia, unPais);
		Geolocalizacion unaGeo = new Geolocalizacion(latitud, longitud, unDomi, unaLoca);

		Terminal laNuevaTerminal = new Terminal(nombre, comuna);
		laNuevaTerminal.setPoint(unaGeo);

		modeloTerminales.persistirTerminal(laNuevaTerminal);
	}

	public List<String> listarAccionesTerminal(Terminal unaTerminal) {
		return unaTerminal.getNombreDeAcciones();
	}

	public void setearAccionParaUnaTerminal(String nombreTerminal, Accion unaAccion) {
		Terminal unaTerminal = modeloTerminales.buscameUnaTerminal(nombreTerminal);
		eliminarUnaTerminal(unaTerminal);
		unaTerminal.addAccion(unaAccion);
		modeloTerminales.persistirTerminal(unaTerminal);

	}

	public void removerAccionParaUnaTerminal(String nombreTerminal, Accion unaAccion) {
		Terminal unaTerminal = modeloTerminales.buscameUnaTerminal(nombreTerminal);
		eliminarUnaTerminal(unaTerminal);
		unaTerminal.quitar(unaAccion);
		modeloTerminales.persistirTerminal(unaTerminal);
	}

}