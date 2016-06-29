package Repositorio;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import ActualizarLocalesComerciales.ActualizadorDeLC;
import AsignarAccionesUsuario.AsignarAccionesUsuarios;
import BancoExterno.ApiDeBancoMock;

public class GestorDeProcesos {
	
	   static Timer timer= new Timer();
	 
public void iniciarScheduleDeProcesos(){

	
	// aca hay que setear fechas
	 
	      timer.schedule(new AsignarAccionesUsuarios(),/*una fecha*/);
	      timer.schedule(new ActualizadorDeRepositorioDePoi(), /*otra fecha*/);
	   }
}
