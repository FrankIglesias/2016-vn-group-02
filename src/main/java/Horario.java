public class Horario {

	private String inicioDeSemana;
	private String finDeSemana;
	private int horaInicial;
	private int horaFinal;
	
	public Horario (int horai, int horaf){
		super();
		this.horaFinal = horaf;
		this.horaInicial = horai;
	}
	public Horario (String inicioSem,String finSem,int horai, int horaf){
		super();
		this.horaFinal = horaf;
		this.horaInicial = horai;
		this.inicioDeSemana = inicioSem;
		this.finDeSemana = finSem;
	}
}