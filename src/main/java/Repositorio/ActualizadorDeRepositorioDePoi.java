package Repositorio;

import java.util.TimerTask;

import ActualizarLocalesComerciales.ActualizadorDeLC;
import BancoExterno.ApiDeBancoMock;

public class ActualizadorDeRepositorioDePoi extends TimerTask {

	RepoPOIs repositorioDePois = RepoPOIs.getInstance();
	public void run() { 
	ActualizadorDeLC actualizadorDeLocalesComerciales = new ActualizadorDeLC();
	actualizadorDeLocalesComerciales.actualizarListaDeLC();
	ApiDeBancoMock actualizadorDeBancos = new ApiDeBancoMock();
	repositorioDePois.agregarVariosPoiDeListaDeBancos(actualizadorDeBancos.obtenerBancos());

	}

}
