package aaron.api.main;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import aaron.api.Lights;
import aaron.api.LightsHelper;
import aaron.api.Request;

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
