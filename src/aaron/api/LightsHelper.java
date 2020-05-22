package aaron.api;

import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class LightsHelper {
	private Lights[] lights;
	private Request lightapi;
	private String lightBaseURL = "/lights";
	private String lightCallURL = "/lights/";
	private String lightBaseStateURL = "/state";

	public LightsHelper(Request apiConnection) {
		this.lightapi = apiConnection;
		this.lights = populateLights();
	}
	
	public Lights[] getLights() {
		return this.lights;
	}
	
	public void updateLights() {
		this.lights = populateLights();
	}
	
	public Lights[] populateLights() {
		Lights[] newLightList = null;
		JSONObject lightList = parseJSON(lightapi.sendRequest("Lights", lightBaseURL, new JSONObject(), "GET"));
		int lightCount = lightList.size();
		newLightList = new Lights[lightCount];
		
		/*This next bit of code finds the ID of the item entries in the Philips Hue API json response. 
		 */
		Set<String> lightMap = lightList.keySet();
		String[] lightsID = new String[lightMap.size()];
		int bufr = 0;
		for(String x: lightMap) {
			lightsID[bufr++] = x;
		}
		
		for(int x=1; x<=lightCount; x++) {
			String id = lightsID[x-1]; //Light ID
			JSONObject lightBuffer = (JSONObject) lightList.get(id);
			String name = lightBuffer.get("name").toString(); //Light Name
			JSONObject lightState = (JSONObject) lightBuffer.get("state"); //Looks inside "state" substring
			String state = lightState.get("on").toString(); //Light on off
			String brightness = lightState.get("bri").toString(); //Light Brightness
			String reachable = lightState.get("reachable").toString(); //If reachable
			boolean connected = Boolean.parseBoolean(reachable);
			JSONObject lightCapabilities = (JSONObject) lightBuffer.get("capabilities"); //Looks inside "capabilities" substring
			JSONObject lightStreaming = (JSONObject) lightCapabilities.get("streaming"); //Looks inside "capabilities" substring
			String renderer = lightStreaming.get("renderer").toString(); //If reachable
			boolean isColor = Boolean.parseBoolean(renderer);
			String hue = null;
			String saturation = null;
			String colorX = null;
			String colorY = null;
			String colormode = null;
			String colortone = null;
			if(isColor) {
				hue = lightState.get("hue").toString(); //Light HUE
				saturation = lightState.get("sat").toString(); //Light SAT
				colormode = lightState.get("colormode").toString(); //Light colormode
				colortone = lightState.get("ct").toString(); //Light CT
				String[] color = lightState.get("xy").toString().replace("[", "").replace("]", "").split(",");
				colorX = color[0]; //Light color-x
				colorY = color[1]; //Light color-y
			}
			newLightList[x-1] = new Lights(id, state, brightness, hue, saturation, colorX, colorY, colortone, colormode, name, connected, isColor);
		}
		return newLightList;
	}
	
	private JSONObject parseJSON(String data) {
		JSONObject jsonData = null;
		try {
			JSONParser jsonParser = new JSONParser();
			Object bufferObject = jsonParser.parse(data);
			jsonData = (JSONObject) bufferObject;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return jsonData;
	}

}
