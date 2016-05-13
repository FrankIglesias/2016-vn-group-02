import java.time.LocalDate;

public class Busqueda {

	LocalDate fecha;
	String terminal;
	String frase;
	double tiempo;

	public Busqueda(String terminal, String frase, double tiempo) {
		this.terminal = terminal;
		this.fecha = LocalDate.now();
		this.frase = frase;
		this.tiempo = tiempo;
	}

	public boolean conFechaDe(LocalDate fecha) {
		return this.fecha.isEqual(fecha);
	}

}
