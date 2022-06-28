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


public class ServicioGoogleDistanceMatrixAPI {
	
	private static final String API_KEY ="";
	
	
	
	//downloading the data
	public String[] getData(String destino)throws Exception{
		String origen = "FlorencioVarela1903SanJusto";
		var url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="+origen+"&destinations="+destino+"&key="+API_KEY;
	    var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
	    var client = HttpClient.newBuilder().build();
	    var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
	    Long distance = 1l;
	    Long time = 1l;
	    //--------------------------------
	    //String agregado
	    String distancia;
	    String tiempo;
	    String localidadDestino;
	    String localidadOrigen;
	    //-----------------------------------
	    String[] respuesta = new String[4];
	    //System.out.println("response = "+response);
	  //parsing json data
		JSONParser jp = new JSONParser();
		JSONObject jo = (JSONObject) jp.parse(response);
		JSONArray  ja = (JSONArray) jo.get("rows");
		//--------------------------------------------
		JSONArray  j2 = (JSONArray) jo.get("destination_addresses");
		JSONArray  j3 = (JSONArray) jo.get("origin_addresses");
		//JSONObject ld = (JSONObject) j2.get(0);
		//JSONObject lo = (JSONObject) j3.get(0);
		localidadDestino = (String) j2.get(0);
		localidadOrigen= (String) j3.get(0);
		jo = (JSONObject) ja.get(0);
		ja = (JSONArray)jo.get("elements");
		jo = (JSONObject)ja.get(0);
		JSONObject je = (JSONObject)jo.get("distance");
		JSONObject jf = (JSONObject)jo.get("duration");
		distance = (Long) je.get("value");
		time = (Long) jf.get("value");
		//---------------------------------------
		//String agregado
		distancia = (String)je.get("text");
		tiempo = (String) jf.get("text");
		//localidadDestino = (String) ld;
		//---------------------------------------
		//System.out.println(ja.toString());
		//System.out.println(je.toString());
		//System.out.println(jf.toString());
		//System.out.println(distance + "mm");
		//System.out.println(time + "ms");
		//---------------------------------------
		//String agregado 
		//System.out.println(distancia);
		//System.out.println(tiempo);
		//-----------------------------------
		//System.out.println(j2);
		//System.out.println(j3);
		//System.out.println(localidadDestino);
		//System.out.println(localidadOrigen);
		respuesta[0] = localidadOrigen;
		respuesta[1] = localidadDestino;
		respuesta[2] = distancia;
		respuesta[3] = tiempo;
		return respuesta;
	}
 	
	
	/*public static void main(String[] args) throws Exception {
		//String source = "Malvinas1700MonteGrande";
		String destination = "Alem171MonteGrande";
		//***************************************************
		String[] respuesta = getData(destination);
		for(int i = 0; i< respuesta.length; i++) {
			System.out.println("Elemento " + i + " del array es : "+ respuesta[i]);
		}
	}
	*/
	
}
