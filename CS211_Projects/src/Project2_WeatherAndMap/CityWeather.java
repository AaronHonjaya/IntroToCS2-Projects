package Project2_WeatherAndMap;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class CityWeather {
	static Scanner sc = new Scanner(System.in);
	
	public static String getCityWeather(String cityName) throws Exception {
		String cityWeather = "";
		
		try {
			String firstPartURL = "https://api.openweathermap.org/data/2.5/weather?q=";
			String secondPartURL ="&appid=2a932c53cb8f5d034162e78bc858dc04"; 
			String unitsIdentifier = "&units=imperial";
			String theURL = firstPartURL + cityName + secondPartURL + unitsIdentifier;

			URL url = new URL(theURL); 
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			JSONParser jsonParser = new JSONParser();
			JSONObject myWeather = (JSONObject)jsonParser.parse(br); 
		
			JSONArray weatherArray = (JSONArray)myWeather.get("weather");
			JSONObject mainInfoArray = (JSONObject)myWeather.get("main");
		
			
	
			//current weather description
			JSONObject weatherInfo = (JSONObject)weatherArray.get(0);
			String currentWeather = (String)weatherInfo.get("description");
			cityWeather += currentWeather + " | ";
			
			try {
				//current temp
				Long currentTemperature = (Long)mainInfoArray.get("temp");
				cityWeather += currentTemperature + "\u00B0F | ";
			}catch (ClassCastException e) {
				double currentTemperature = (double)mainInfoArray.get("temp");
				cityWeather += currentTemperature + "\u00B0F | ";
			}
			
			try {
				//Low Temp
				Long lowTemperature = (Long)mainInfoArray.get("temp_min");
				cityWeather += lowTemperature + " \u00B0F | ";
			}catch (ClassCastException e) {
				double lowTemperature = (double)mainInfoArray.get("temp_min");
				cityWeather += lowTemperature + " \u00B0F | ";
				
			}
			
			
			try {
				//High Temp
				Long highTemperature = (Long) mainInfoArray.get("temp_max");
				cityWeather += highTemperature + " \u00B0F | ";
			}catch (ClassCastException e) {
				double highTemperature = (double) mainInfoArray.get("temp_max");
				cityWeather += highTemperature + " \u00B0F | ";

			}
			

			try {
				//Wind Speed
				JSONObject windInfo = (JSONObject)myWeather.get("wind");
				Long windSpeed = (Long)windInfo.get("speed");
				cityWeather += windSpeed + " | ";
			}catch (ClassCastException e) {
				JSONObject windInfo = (JSONObject)myWeather.get("wind");
				double windSpeed = (double)windInfo.get("speed");
				cityWeather += windSpeed + " | ";
			}
			
			try {
				//Humidity
				Long humidity = (Long)mainInfoArray.get("humidity");
				cityWeather += humidity + " | ";
				
			}
			catch (ClassCastException e) {
				double humidity = (double)mainInfoArray.get("humidity");
				cityWeather += humidity + " | ";
			
			}
				
			
			//Current Date and Time in Bellevue
			LocalDateTime dateObj = LocalDateTime.now();
		    DateTimeFormatter timeObj = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
		    String currentTime = dateObj.format(timeObj); 
		    
		    cityWeather += currentTime + " |";

			
		}catch(Exception ex) {
			//System.out.println(ex);
			
		}
		return cityWeather;
	}
	
	public static void getCityWeather() throws Exception {
		System.out.println("Type in the name of a city: ");
		String cityName = sc.nextLine();
		String cityWeather = getCityWeather(cityName);
		while(cityWeather.isBlank()) {
			System.out.println("That is an Invalid City. Please try again or type EXIT to stop");
			cityName = sc.nextLine();
			if(cityName.equalsIgnoreCase("exit")) {
				System.out.println("Thank you for your time");
				break;
			}
			cityWeather = getCityWeather(cityName);		
		}
		System.out.println(cityWeather);
		
		String mapType;
		int zoom;
		System.out.println("Select a Map type 1) roadmap 2) satellite");
		int input = sc.nextInt();
		if(input == 2) {
			mapType = "satellite";
		}else {
			mapType = "roadmap";
		}
		System.out.println("Select a zoom level from 0-21");
		zoom = sc.nextInt();
		new Map(cityName, cityWeather, mapType, zoom);
	}
	
		
}
