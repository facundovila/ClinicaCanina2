package clinicacanina.servicios;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.android.Context;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import clinicacanina.repositorios.GoogleAPIGateway;
import clinicacanina.repositorios.GoogleAPIGatewayImpl;
import clinicacanina.repositorios.Trayecto;


public class ServicioGoogleDistanceMatrixAPI {
	
	private GoogleAPIGateway googleAPIGateway = new GoogleAPIGatewayImpl();
	
	public Trayecto getDistance(String destino) throws Exception {
		return googleAPIGateway.calcularTrayecto(destino);
	}
}
