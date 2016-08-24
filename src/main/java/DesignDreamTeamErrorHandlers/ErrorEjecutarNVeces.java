package DesignDreamTeamErrorHandlers;

import java.util.Date;

public class ErrorEjecutarNVeces implements DDTErrorHandler {

	private int numeroFinal;

	public void setNumeroFinal(int numero) {
		numeroFinal = numero;
	}

	@Override
	public void ejecutarAccion(Date fecha, Runnable proceso) {
		int numeroDeVeces = 0;
		try {
			proceso.run();

		} catch (RuntimeException e) {
			if (numeroDeVeces < numeroFinal)
				numeroDeVeces++;
		}
	}

}
