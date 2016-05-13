import java.time.LocalDate;

public class Busqueda {

	LocalDate fecha;
	Terminal terminal;
	String frase;
	double tiempo;

	public Busqueda(Terminal terminal, String frase, double tiempo) {
		this.terminal = terminal;
		this.fecha = LocalDate.now();
		this.frase = frase;
		this.tiempo = tiempo;
	}

	public boolean conFechaDe(LocalDate fecha) {
		return this.fecha.isEqual(fecha);
	}

}
