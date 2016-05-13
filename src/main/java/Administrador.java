
public class Administrador {
	int tiempo;

	Administrador (){
		super();
		tiempo = 5;
	}
	void notificar(Busqueda busqueda){
		if((int)busqueda.tiempo > tiempo){
			return;
		}
			
	}
	
	void setTiempo(int n){
		tiempo = n;
	}
}
	
	
