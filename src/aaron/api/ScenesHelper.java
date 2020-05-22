package aaron.api;

import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ScenesHelper {
	private Scenes[] scenes;
	private Request lightapi;
	private String sceneBaseURL = "/scenes";
	private String sceneCallURL = "/scenes/";
	private String sceneBaseModifyURL = "/";
	private String sceneLightStateURL = "/lightstates";

	public ScenesHelper(Request apiConnection) {
		this.lightapi = apiConnection;
		this.scenes = populateScenes();
	}
	
	public Scenes[] getScenes() {
		return this.scenes;
	}
	
	public void updateScenes() {
		this.scenes = populateScenes();
	}
	
	public Scenes[] populateScenes() {
		Scenes[] newSceneList = null;
		JSONObject sceneList = parseJSON(lightapi.sendRequest("Scenes", sceneBaseURL, new JSONObject(), "GET"));
		int runCount = sceneList.size();
		newSceneList = new Scenes[runCount];
		
		/*This next bit of code finds the ID of the item entries in the Philips Hue API json response. 
		 */
		Set<String> sceneMap = sceneList.keySet();
		String[] sceneID = new String[sceneMap.size()];
		int bufr = 0;
		for(String x: sceneMap) {
			sceneID[bufr++] = x;
		}
		
		for(int x = 1; x<=runCount; x++) {
			String id = sceneID[x-1]; //Scene ID
			JSONObject sceneBuffer = (JSONObject) sceneList.get(id); //Looks inside the scene ID
			String name = sceneBuffer.get("name").toString(); //Scene Name
			String type = sceneBuffer.get("type").toString(); //Scene Type
			String group = null; //Scene Group
			String locked = sceneBuffer.get("locked").toString(); //Scene Locked
			String picture = sceneBuffer.get("picture").toString(); //Scene Picture
			String image = null; //Scene Image, does not exist for LightScene type
			String lastupdated = sceneBuffer.get("lastupdated").toString(); //Scene Last Updated
			String version = sceneBuffer.get("version").toString(); //Scene Version
			String appdata_version = null; //Appdata Version, does not exist for LightScene type
			String appdata_data = null; //Appdata Data, does not exist for LightScene type		
			JSONArray sceneLights = (JSONArray) sceneBuffer.get("lights"); //Look inside "state" substring
			Object[] lightBuffer = sceneLights.toArray();
			String[] sceneArray = new String[sceneLights.size()];
			int pos = 0;
			for(Object obj : lightBuffer) {
				sceneArray[pos] = (String) obj;
				pos++;
			}
			if(type.equalsIgnoreCase("GroupScene")) { //If scene type is a GroupScene
				group = sceneBuffer.get("group").toString(); //Scene Group
				try {
					image = sceneBuffer.get("image").toString(); //Get its image
					JSONObject sceneAppdata = (JSONObject) sceneBuffer.get("appdata"); //Looks inside the scene "appdata"
					appdata_version = sceneAppdata.get("version").toString();
					appdata_data = sceneAppdata.get("data").toString();
				} catch (NullPointerException nullPointer) {
					//System.out.println("Some scenes that loaded contain incomplete data, probably made by an Apple Device lol.");
					//Ignore this error
				}
			}
			
			newSceneList[x-1] = new Scenes(id, name, type, group, locked, appdata_version, appdata_data, picture, image, lastupdated, version, sceneArray);
			
		}
		return newSceneList;
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
