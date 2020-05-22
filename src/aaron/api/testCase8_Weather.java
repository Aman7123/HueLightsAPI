package aaron.api;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class testCase8_Weather {
	public static Groups[] groups;
	private static String weatherApiKey = "1f7c653280fb4dadb0c43924201105";
	private static String weatherApiBaseURL = "http://api.weatherapi.com/v1";
	private static String weatherApiCurrentURL = "/current.json";
	private static String zipCode = "21703";

	public static void main(String[] args) {
		Request lightapi = new Request("192.168.1.2", "WtT1QWLi5FRRewFZtfvFcqDZ8C04z-m0Dn-NeSDU");
		GroupsHelper helper = new GroupsHelper(lightapi);
		groups = helper.getGroups();
		
		String websiteResponce = lightapi.getData(weatherApiBaseURL + weatherApiCurrentURL + "?key=" + weatherApiKey + "&q=" + zipCode);
		JSONObject weatherData = (JSONObject) parseJSON(websiteResponce);
		System.out.println(weatherData.toJSONString());
	}
	
	private static JSONObject parseJSON(String data) {
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
