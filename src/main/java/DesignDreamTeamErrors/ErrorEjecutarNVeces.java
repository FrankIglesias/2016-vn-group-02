package DesignDreamTeamErrors;

import java.util.Date;
import java.util.TimerTask;

public class ErrorEjecutarNVeces implements ErrorHandler{

	private static int numeroFinal;
	 
	public static void setNumeroFinal(int numero) {
	numeroFinal = numero;
	}
	
	public void ejecutarAccion(Date fecha, TimerTask proceso) {
		int numeroDeVeces = 0;
		try{
			proceso.run();
			
		}catch(RuntimeException e){
			if(numeroDeVeces<numeroFinal)
			numeroDeVeces++;	
		}
		
		
	}


}
