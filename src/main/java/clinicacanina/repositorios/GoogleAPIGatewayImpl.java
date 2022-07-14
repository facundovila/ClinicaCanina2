package clinicacanina.repositorios;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GoogleAPIGatewayImpl implements GoogleAPIGateway {
	private static final String API_KEY = "AIzaSyCDh2CzrRsSLv6D4Dd2Zj6bhGubnODsdYo";
	private final String origen = "FlorencioVarela1903SanJusto";

	@Override
	public Trayecto calcularTrayecto(String destino) throws Exception {

		var response = consumeGoogleAPI(destino);
		return parseJSON(response);

	}

	private Trayecto parseJSON(String json) throws ParseException {
		Long distance = 1l;
		Long time = 1l;

		String distancia;
		String tiempo;
		String localidadDestino;
		String localidadOrigen;

		JSONParser jp = new JSONParser();
		JSONObject jo = (JSONObject) jp.parse(json);
		JSONArray ja = (JSONArray) jo.get("rows");

		JSONArray j2 = (JSONArray) jo.get("destination_addresses");
		JSONArray j3 = (JSONArray) jo.get("origin_addresses");

		localidadDestino = (String) j2.get(0);
		localidadOrigen = (String) j3.get(0);
		jo = (JSONObject) ja.get(0);
		ja = (JSONArray) jo.get("elements");
		jo = (JSONObject) ja.get(0);
		JSONObject je = (JSONObject) jo.get("distance");
		JSONObject jf = (JSONObject) jo.get("duration");
		distance = (Long) je.get("value");
		time = (Long) jf.get("value");

		distancia = (String) je.get("text");
		tiempo = (String) jf.get("text");

		Trayecto trayecto = new Trayecto();
		trayecto.setLocalidadOrigen(localidadOrigen);
		trayecto.setLocalidadDestino(localidadDestino);
		trayecto.setDistancia(distancia);
		trayecto.setTiempo(tiempo);

		return trayecto;
	}

	private String consumeGoogleAPI(String destino) throws IOException, InterruptedException {
		var url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + origen + "&destinations="
				+ destino + "&key=" + API_KEY;
		var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
		var client = HttpClient.newBuilder().build();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
		return response;
	}

}
