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
		SemVamoASincronizarno_signal();
	}

	private void SemVamoASincronizarno_signal() {
		GestorDeProcesos.sem.release();
	}

}
