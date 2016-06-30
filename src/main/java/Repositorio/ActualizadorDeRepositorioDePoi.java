package Repositorio;

import java.util.TimerTask;

import BancoExterno.ApiDeBancoMock;

public class ActualizadorDeRepositorioDePoi extends TimerTask {

	RepoPOIs repositorioDePois = RepoPOIs.getInstance();

	public void run() {
		System.out.println("Comenzando a agregar bancos...");
		// ActualizadorDeLC actualizadorDeLocalesComerciales = new
		// ActualizadorDeLC();
		// actualizadorDeLocalesComerciales.actualizarListaDeLC();
		ApiDeBancoMock actualizadorDeBancos = new ApiDeBancoMock();
		repositorioDePois.agregarVariosPoiDeListaDeBancos(actualizadorDeBancos.obtenerBancoDesdeString());
		System.out.println("Finalizado agregar bancos...");
		GestorDeProcesos.sem.release();
	}

}
