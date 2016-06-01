


import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/* API DE BANCOS
 * http://private-96b476-ddsutn.apiary-mock.com/banks?banco=banco&servicio=servicio
 * 
 * */

public class ApiDeBancoReal implements ApiDeBancoInterface {
	private Client client;
	private static final String API_GOOGLE = "http://private-96b476-ddsutn.apiary-mock.com/";
	private static final String RESOURCE = "banks";

	// Inicializacion del cliente.
	public ApiDeBancoReal() {
		this.client = Client.create();
	}

	public ClientResponse getBookByFilter(String filter, String value) {
		WebResource recurso = this.client.resource(API_GOOGLE).path(RESOURCE);
		WebResource recursoConParametros = recurso.queryParam("banks", filter + ": " + value);
		WebResource.Builder builder = recursoConParametros.accept(MediaType.APPLICATION_JSON);
		ClientResponse response = builder.get(ClientResponse.class);
		return response;
	}

	public ClientResponse getBookByFilter(String filter, String value, String fields) {
		ClientResponse response = this.client.resource(API_GOOGLE).path(RESOURCE)
				.queryParam("banks", filter + ":" + value).queryParam("fields", fields)
				.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		return response;
	}

	public ClientResponse getBookAndSendHeader(String filter, String value, String header, String headerValue) {
		ClientResponse response = this.client.resource(API_GOOGLE).path(RESOURCE)
				.queryParam("banks", filter + ":" + value).header(header, headerValue)
				.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		return response;
	}

	public Banco obtenerBancos() {
		
		String json = "{" + "\"banco\": \"Banco de la Plaza\"," + "\"x\": -35.9338322," + "\"y\": 72.348353,"
				+ "\"sucursal\": \"Avellaneda\"," + "\"gerente\": \"Javier Loeschbor\","
				+ "\"extra_property\": \"Does not fail because mapper is configured to not fail with unknown properties\""
				+ "}";
		JsonFactory jsonFactory = new JsonFactory();
		BancoTrucho banco = jsonFactory.fromJson(json, BancoTrucho.class);
		Banco bancoSistema = new Banco(new Geolocalizacion(banco.x, banco.y, null, null), banco.banco,
				new ArrayList<String>(), null);
		return bancoSistema;
	}
	public String obtenerStream(){
		ClientResponse response = this.getBookByFilter("sucursal", "Avellaneda");
		return response.getEntity(String.class);
	}
}
