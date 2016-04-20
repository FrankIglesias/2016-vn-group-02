

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EncuentraSegunPalabraClaveTest {
	 
	public Domicilio domicilioBanco;
	public Localidad localidadBanco;
	public Geolocalizacion geolocalizacionBanco;
	public ArrayList<String> palabrasClaveBanco = new ArrayList<String>();
	public Banco banco;
	public Domicilio domicilioColectivo;
	public Localidad localidadColectivo;
	public Geolocalizacion geolocalizacionColectivo;
	public ArrayList<String> palabrasClaveColectivo = new ArrayList<String>();
	public Colectivo colectivo;
	public List<POI> puntosDeInteres = new ArrayList<POI>();
	public Buscador sistPrueba;
	
	@Before 
	public void init(){  
		sistPrueba = new Buscador();
		
		domicilioBanco = new Domicilio("Bolivia","El Gaucho y Estrella Federal","6058","PB","","1419");
		localidadBanco = new Localidad("Capital Federal", "Buenos Aires", "Argentina");
		geolocalizacionBanco = new Geolocalizacion(-34.5735632, -58.5105945, domicilioBanco, localidadBanco);
		banco = new Banco(geolocalizacionBanco, "Banco Rio", palabrasClaveBanco, null);
		banco.addPalabraClave("dinero");
		banco.addPalabraClave("cuenta");
		banco.addPalabraClave("banco");
		banco.addPalabraClave("banelco");
		
		domicilioColectivo = new Domicilio("Manuel Alvarez Prado","Bolivia y Av. de Los Constituyentes","2402-2600","PB","","1419");
		localidadColectivo = new Localidad("Capital Federal", "Buenos Aires", "Argentina");
		geolocalizacionColectivo = new Geolocalizacion(-34.5730928, -58.511029, domicilioColectivo, localidadColectivo);
		colectivo = new Colectivo(geolocalizacionColectivo, "Colectivo", palabrasClaveColectivo,"127", null);
		colectivo.addPalabraClave("colectivo");
		colectivo.addPalabraClave("transporte");
		colectivo.addPalabraClave("publico");
		colectivo.addPalabraClave("rueditas");
		colectivo.addPalabraClave("127");
		
		puntosDeInteres.add(colectivo);
		puntosDeInteres.add(banco);
		
	}
	@Test
	public void encontrarPOISegunPalabra() {
		String fraseABuscar = "quiero un banco malo";
		Buscador.setList(puntosDeInteres);
		Buscador.buscarSegunPalabraClave(fraseABuscar);
		
		Assert.assertTrue(Buscador.buscarSegunPalabraClave(fraseABuscar).contains(banco)); 
		Assert.assertEquals("Cantidad de elementos en el array",1,Buscador.buscarSegunPalabraClave("banelco").size());
		
		
	}
	
	
}
