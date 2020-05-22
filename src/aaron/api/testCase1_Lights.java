package aaron.api;

import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class testCase1_Lights {
	public static Lights[] lights;
	private static String webpageRequest = "/lights";
	private static String newLightsURL = "/lights/new";

	public static void main(String[] args) throws ParseException {
		Request lightapi = new Request("192.168.1.2", "WtT1QWLi5FRRewFZtfvFcqDZ8C04z-m0Dn-NeSDU");
		LightsHelper helper = new LightsHelper(lightapi);
		lights = helper.getLights();
		for(Lights x : lights) {
			System.out.println(x);
		}
		
		String newLights = lightapi.sendRequest("Lights", newLightsURL, new JSONObject(), "GET");
		System.out.println(newLights);
		
		String newLightsSearch = lightapi.sendRequest("Lights", webpageRequest, new JSONObject(), "POST");
		System.out.println(newLightsSearch);
	}
}
