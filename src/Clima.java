import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

public class Clima {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Scanner sc = new Scanner(System.in);
			
			System.out.print("Ingrese una ciudad: ");
			StringBuilder ciudad = new StringBuilder(sc.nextLine());
			System.out.println();
			
			EliminarEspacios(ciudad);
			
			String respuesta = getHTML("http://api.openweathermap.org/data/2.5/weather?q="+ciudad+"&appid=a1c94362254081c926bf128d6819d5f5");
			
			System.out.print("\n1- Detalles Simples"+"\n2- Más Detalles"+"\nIngrese una opción: ");
			int opc = sc.nextInt();
			
			switch(opc){
				case 1: 
					simpleWeather(respuesta, ciudad);
					break;
					
				case 2:
					complexWeather(respuesta, ciudad);
					break;
					
				default: System.out.println("\nOpción no válida");
					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getHTML(String urlToRead) throws Exception {
		StringBuilder result = new StringBuilder();
		URL url = new URL(urlToRead);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null) {
		   result.append(line);
		}
		rd.close();
		return result.toString();
	}
	
	public static void EliminarEspacios(StringBuilder ciudad){
		for(int cont = 0; cont<ciudad.length(); cont++){
			if(ciudad.charAt(cont)==' ')
				ciudad.deleteCharAt(cont);
		}
	}
	
	public static void simpleWeather(String respuesta, StringBuilder ciudad){
		JSONObject obj = new JSONObject(respuesta);
		System.out.println("\nCiudad: "+ciudad+"\n");
		double lat = obj.getJSONObject("coord").getDouble("lat");
		System.out.println("Latitud: "+lat);
		
		double lon = obj.getJSONObject("coord").getDouble("lon");
		System.out.println("Longitud: "+lon);
		
		double temp = obj.getJSONObject("main").getDouble("temp");
		temp -= 273.15;
		System.out.println("Temperatura: "+temp+"°C");
		
		double pres = obj.getJSONObject("main").getDouble("pressure");
		System.out.println("Presión del aire: "+pres+" kPa");
		
		double hum = obj.getJSONObject("main").getDouble("humidity");
		System.out.println("Humedad: "+hum);
	}
	
	public static void complexWeather(String respuesta, StringBuilder ciudad){
		JSONObject obj = new JSONObject(respuesta);
		System.out.println("\nCiudad: "+ciudad+"\n");
		double lat = obj.getJSONObject("coord").getDouble("lat");
		System.out.println("Latitud: "+lat);
		
		double lon = obj.getJSONObject("coord").getDouble("lon");
		System.out.println("Longitud: "+lon);
		
		double temp = obj.getJSONObject("main").getDouble("temp");
		temp -= 273.15;
		System.out.println("Temperatura: "+temp+"°C");
		
		double pres = obj.getJSONObject("main").getDouble("pressure");
		System.out.println("Presión del aire: "+pres+" kPa");
		
		double hum = obj.getJSONObject("main").getDouble("humidity");
		System.out.println("Humedad: "+hum);
		
		double tempmin = obj.getJSONObject("main").getDouble("temp_min");
		tempmin -= 273.15;
		System.out.println("Temperatura Mínima: "+tempmin+"°C");
		
		double tempmax = obj.getJSONObject("main").getDouble("temp_max");
		tempmax -= 273.15;
		System.out.println("Temperatura Máxima: "+tempmax+"°C");
		
		double velwind = obj.getJSONObject("wind").getDouble("speed");
		System.out.println("Velocidad del Viento: "+velwind+" m/s");
		
		String pais = obj.getJSONObject("sys").getString("country");
		System.out.println("País: "+pais);
	}
}