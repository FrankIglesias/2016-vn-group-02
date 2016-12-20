package Controllers;

import java.util.List;

import AsignarAccionesUsuario.Accion;
import Repositorios.RepoTerminales;
import Repositorios.Terminal;

public class ControllerRepoTerminales {
	
	private static ControllerRepoTerminales instancia;
	public RepoTerminales modeloTerminales = RepoTerminales.getInstance();
	
	public static ControllerRepoTerminales getInstance() {
		if (instancia == null) {
			instancia = new ControllerRepoTerminales ();
		}
		return instancia;
	}
	


	public List<Terminal> listarTerminales(int comuna) {

		return modeloTerminales.obtenerTerminales(comuna);
	}
	
	public void eliminarUnaTerminal(Terminal unaTerminal) {
		modeloTerminales.eliminarUnaTerminal(unaTerminal);
	}
	
	public void agregarUnaTerminal(String nombre, int comuna) {
		Terminal unaTerminal = new Terminal(nombre, comuna);
		modeloTerminales.persistirTerminal(unaTerminal);
	}
	
	public void editarUnaTerminal(Terminal terminalVieja, String nombre, int comuna) {
		Terminal unaTerminal;
		
		if(nombre.isEmpty()) 
			unaTerminal = new Terminal(terminalVieja.getNombre(), comuna);
		
		if(comuna == -1)
			unaTerminal = new Terminal(nombre, terminalVieja.getComuna());
		else 
			unaTerminal = new Terminal(nombre, comuna);
		
		modeloTerminales.persistirTerminal(unaTerminal);
	}
	
	public void setearAccionParaUnaTerminal(Terminal unaTerminal, Accion unaAccion) {
		unaTerminal.addAccion(unaAccion);
	}
	
	public void removerAccionParaUnaTerminal(Terminal unaTerminal, Accion unaAccion) {
		unaTerminal.quitar(unaAccion);
	}
	
}