package Repositorio;

import java.util.TimerTask;

import BancoExterno.ApiDeBancoMock;

public class ActualizadorDeRepositorioDePoi extends TimerTask {

	RepoPOIs repositorioDePois = RepoPOIs.getInstance();

	public void run() {
		System.out.println("Comenzando a agregar bancos...");
		ApiDeBancoMock actualizadorDeBancos = new ApiDeBancoMock();
		repositorioDePois.agregarVariosPoiDeListaDeBancos(actualizadorDeBancos.obtenerBancoDesdeString());
		System.out.println("Finalizado agregar bancos...");
		
		System.out.println("Comenzando a agregar cgps...");
		repositorioDePois.agregarVariosPoi(RepositorioCGPExternoAdapter.obtenerCGPDesdeRepositorioExterno("4637-2355"));
		System.out.println("Finalizado agregar cgps...");
		SemVamoASincronizarno_signal();
	}

	private void SemVamoASincronizarno_signal() {
		GestorDeProcesos.sem.release();
	}

}
