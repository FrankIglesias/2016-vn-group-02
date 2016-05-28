import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.mail.Session;

public class Terminal {

	private String nombre;
	private static List<Integer> reporteParcialPorTerminal = new ArrayList<Integer>();
	private GestorDeMail gestorDeMail;
	private String mailAdmin;

	public Terminal(String nombre) {
		this.nombre = nombre;
	}

	public void setGestorDeMail(Session session, String usuarioFrom, String contrasenia) {
		this.gestorDeMail = new GestorDeMail(session, usuarioFrom, contrasenia);
	}

	public int cantidadDeResultadosPorBusqueda(List<POI> puntosSegunPalabra) {
		return puntosSegunPalabra.size();
	}

	public void addResultadosParcialesAlReporte(List<POI> puntosSegunPalabra) {
		reporteParcialPorTerminal.add(cantidadDeResultadosPorBusqueda(puntosSegunPalabra));
	}

	public List<Integer> getReporteParcialPorTerminal() {
		return reporteParcialPorTerminal;
	}

	public int sumarResultados(List<Integer> enteros) {
		int resultado = 0;
		for (Integer entero : enteros) {
			resultado += entero;
		}
		return resultado;
	}

	public int resultadosTotales() {
		return this.sumarResultados(reporteParcialPorTerminal);
	}

	public boolean avisaAlAdminTiempoExcedido(double tiempo, String frase, LocalDate fecha, String terminal) {

		return gestorDeMail.enviarMail(Message.RecipientType.TO, mailAdmin, "Tiempo Excedido",
				"La busqueda con la frase " + frase + " realizada en la fecha " + fecha + ",y en la terminal "
						+ terminal + "se ha excedido " + tiempo + "milisegundos");
	}

	public String getNombre() {
		return nombre;
	}
}
