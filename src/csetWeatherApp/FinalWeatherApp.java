package csetWeatherApp;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
//Example API Call
//http://api.openweathermap.org/data/2.5/forecast?q=Philadelphia,US&APPID=055070dbf102db3cb164033826eb1e3a&units=imperial
public class FinalWeatherApp{

	public static void main(String[] args) {
		String appID = "055070dbf102db3cb164033826eb1e3a";
//  Add after the question mark for future client ease of use:		\nEx: Lancaster, PA, US
		System.out.println("This is UNSC A.I. Serial Number CTN0452-9. Who's Weather Do You Want To Check Today?");
		Scanner sc = new Scanner(System.in);
		String location = sc.nextLine();
		System.out.println("I thought you'd never ask.");
		String response = "";
		try {
			URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + location + "&APPID="+ appID + "&units=imperial");
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
				//Reads JSON from open weather map
				Scanner scan = new Scanner(url.openStream());
				while(scan.hasNext()) {
					response += scan.nextLine();
				}
				scan.close();
			//Shows the JSON Response
			//System.out.println(response);
				
				
				//Reads the data breaks it into key value pairs
				JSONParser parse = new JSONParser();				
				JSONObject jobj = (JSONObject)parse.parse(response);
				//Grabs the city name
				String cityName = (String) jobj.get("name"); 
				//Prints the city name
		        System.out.println("\n" + cityName); 
		        JSONArray jsonarr_1 = (JSONArray) jobj.get("weather");
				//Gets data for array
				for(int i=1; i < jsonarr_1.size(); i++) {
					//Stores in an array
					JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(i);
					//Store the JSON object in JSON array as objects (For level 2 array element i.e Address Components)
					System.out.println("Status: " +jsonobj_1.get("main"));
					System.out.println("Description: " +jsonobj_1.get("description"));
				}
		        //Reads through the "main" object and outputs the data
		        Map mainTemp = ((Map)jobj.get("main"));
		        Iterator<Map.Entry> itr1 = mainTemp.entrySet().iterator(); 
		        while (itr1.hasNext()) { 
		            Map.Entry pair = itr1.next(); 
		            System.out.println(pair.getKey() + " : " + pair.getValue()); 
		        } 
				conn.disconnect();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	}
}