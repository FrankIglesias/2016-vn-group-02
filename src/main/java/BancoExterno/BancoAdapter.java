package BancoExterno;

import java.util.ArrayList;


public class BancoAdapter {

	public int id;
	public String banco;
	public double x;
	public double y;
	public String sucursal;
	public String gerente;
	public ArrayList<String> servicios = new ArrayList<String>();
	
	public String getBanco() {
		return banco;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public String getSucursal() {
		return sucursal;
	}
	public String getGerente() {
		return gerente;
	}
	public ArrayList<String> getServicios() {
		return servicios;
	}
	
	public int getId() {
		return id;
	}
	
}
