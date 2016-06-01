import java.time.LocalDate;

public class Busqueda {

	LocalDate fecha;
	Terminal terminal;
	String frase;
	double tiempo;
	double tiempoMax = 5;

	public Busqueda(Terminal terminal, String frase, double tiempo) {
		this.terminal = terminal;
		this.fecha = LocalDate.now();
		this.frase = frase;
		this.tiempo = tiempo;
		this.analizaElTiempoDeBusqueda();
	}

	public boolean conFechaDe(LocalDate fecha) {
		return this.fecha.isEqual(fecha);
	}

	public void analizaElTiempoDeBusqueda() {
		if (tiempo >= tiempoMax) {                    
			terminal.avisaAlAdminTiempoExcedido((tiempoMax - tiempo), frase, fecha, terminal.getNombre());
		}

	}
}
