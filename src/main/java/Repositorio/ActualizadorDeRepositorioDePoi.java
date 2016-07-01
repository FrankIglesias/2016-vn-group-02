package Repositorio;

import java.util.Date;
import java.util.TimerTask;

import BancoExterno.ApiDeBancoMock;
import CGPExterno.RepositorioCGPExternoAdapter;
import DesignDreamTeamErrors.ErrorHandler;
import DesignDreamTeamProcesses.GestorDeProcesos;
import DesignDreamTeamProcesses.DesignDreamTeamProcess;

public class ActualizadorDeRepositorioDePoi extends DesignDreamTeamProcess {

	public ActualizadorDeRepositorioDePoi(ErrorHandler accion, Date date) {
		super(accion, date);
	}

	RepoPOIs repositorioDePois = RepoPOIs.getInstance();
	ErrorHandler AccionDeError;

	public void setAccionDeError(ErrorHandler accionDeError) {
		AccionDeError = accionDeError;
	}

	public void run() {
		try {
			System.out.println("Comenzando a agregar bancos...");
			ApiDeBancoMock actualizadorDeBancos = new ApiDeBancoMock();
			repositorioDePois.agregarVariosPoiDeListaDeBancos(actualizadorDeBancos.obtenerBancoDesdeString());
			System.out.println("Finalizado agregar bancos...");

			System.out.println("Comenzando a agregar cgps...");
			repositorioDePois
					.agregarVariosPoi(RepositorioCGPExternoAdapter.obtenerCGPDesdeRepositorioExterno("4637-2355"));
			System.out.println("Finalizado agregar cgps...");
			
		} catch (RuntimeException e) {
			AccionDeError.ejecutarAccion(new Date(), this);

		}
		SemVamoASincronizarno_signal();
	}

	private void SemVamoASincronizarno_signal() {
		GestorDeProcesos.sem.release();
	}

}
