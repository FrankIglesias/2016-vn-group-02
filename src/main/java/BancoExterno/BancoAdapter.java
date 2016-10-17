package BancoExterno;

import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;


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
