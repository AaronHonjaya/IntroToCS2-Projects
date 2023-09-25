package Project2_WeatherAndMap;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Map {
	static String html;
	static String mapFileName="myMap.html";
	
	//The string below is blank because the key I used was my professors, and he asked me not to share it. 
	//The code as is will not work.
	//To create your own key, use this website:  
	//https://developers.google.com/maps/documentation/embed/get-api-key
	private static final String googleMapsKey = ""; 
	 
	public Map (String cityName, String weatherInfo, String mapType, int zoom) throws IOException {

	    cityName= cityName.toUpperCase();
	    writeHTML(cityName, weatherInfo, mapType, zoom);
	    String url = mapFileName;   //myMap.html is created in the project folder
	    File htmlFile = new File(url);
	    Desktop.getDesktop().browse(htmlFile.toURI());
	}
	
	public static void writeHTML(String cityName, String weatherNow, String mapType, int zoom) {
		html="<!DOCTYPE html>"
		+ "<html>"
		+ "<body>"
		+ "<h2>"
		+ "["+ cityName+"] " + weatherNow
		+ "</h2>"
		+ "<iframe"
		+ "  width=1200"
		+ "  height=900"
		+ "  style=border:0"
		+ "  loading=lazy"
		+ "  allowfullscreen"
		+ "  referrerpolicy=\"no-referrer-when-downgrade\""
		+ "src=\"https://www.google.com/maps/embed/v1/place?key=" + googleMapsKey + "&q="+ cityName +"&zoom="+ zoom +"&maptype=" + mapType+"\""
		+ "</iframe>"            
		+ "</body>"
		+ "</html>";

		File f= new File (mapFileName);
		try {
		   BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		   bw.write(html);
		   bw.close();
		} catch (IOException e) {
		  e.printStackTrace();
		}
		}

}
