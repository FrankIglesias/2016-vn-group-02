import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import TypePois.Local;

public class RepoLocalesComerciales {
	ArrayList<Local> listaLC = new ArrayList<Local>();
	
	public List<Local> tieneUnLocalConNombre(String nombre){
		return listaLC.stream().filter(unLocal -> unLocal.getNombre().equals(nombre)).collect(Collectors.toList());
	}
	
	public void actualizarLocal(String nombre, ArrayList<String> palabrasClave){
		
		Local localAModificar = (this.tieneUnLocalConNombre(nombre)).get(0);
		listaLC.remove(localAModificar);
		
		localAModificar.setNombre(nombre);
		localAModificar.setPalabrasClave(palabrasClave);
		listaLC.add(localAModificar);
		
	}
}
