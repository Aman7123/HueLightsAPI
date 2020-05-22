package aaron.api;

import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GroupsHelper {
	private Groups[] groups;
	private Request lightapi;
	private String groupBaseURL = "/groups";
	private String groupCallURL = "/groups/";
	private String groupMasterURL = "/groups/0";
	private String groupBaseActionURL = "/action";
	private String groupMasterActionURL = "/groups/0/action";

	public GroupsHelper(Request apiConnection) {
		this.lightapi = apiConnection;
		this.groups = populateGroups();
	}
	
	public Groups[] getGroups() {
		return this.groups;
	}
	
	public void updateGroups() {
		this.groups = populateGroups();
	}
	
	public Groups[] populateGroups() {
		Groups[] newGroupList = null;
		JSONObject groupList = parseJSON(lightapi.sendRequest("Groups", groupBaseURL, new JSONObject(), "GET"));
		int runCount = groupList.size();
		newGroupList = new Groups[runCount+1];
		newGroupList[0] = new Groups("0", "Default", "LightGroup", "true", "", "", "", "", "", "", "", "", "", "", "", "", "", new String[0]);
		
		/*This next bit of code finds the ID of the item entries in the Philips Hue API json response. 
		 */
		Set<String> groupMap = groupList.keySet();
		String[] groupID = new String[groupMap.size()];
		int bufr = 0;
		for(String x: groupMap) {
			groupID[bufr++] = x;
		}
		
		for(int x = 1; x<=runCount; x++) {
			String id = groupID[x-1]; //group ID
			JSONObject groupBuffer = (JSONObject) groupList.get(id); //Look inside main substring
			String name = groupBuffer.get("name").toString(); //group Name
			String type = groupBuffer.get("type").toString(); //group Type
			
			
			JSONArray groupLights = (JSONArray) groupBuffer.get("lights"); //Look inside "state" substring
			Object[] lightBuffer = groupLights.toArray();
			String[] lightArray = new String[groupLights.size()];
			int pos = 0;
			for(Object obj : lightBuffer) {
				lightArray[pos] = (String) obj;
				pos++;
			}
			
			JSONObject groupAction = (JSONObject) groupBuffer.get("action"); //Look inside "action" substring
			int actionGroupSize = groupAction.size();
			String state = groupAction.get("on").toString(); //group On Off
			String alert = groupAction.get("alert").toString(); //group Saturation
			String brightness = null;
			String hue = null;
			String saturation = null;
			String effect = null;
			String colorX = null;
			String colorY = null;
			String colortone = null;
			String colormode = null;
			if(actionGroupSize > 2) {
				brightness = groupAction.get("bri").toString(); //group BRI
			}
			if(actionGroupSize > 5) {
				hue = groupAction.get("hue").toString(); //group HUE
				saturation = groupAction.get("sat").toString(); //group Saturation
				effect = groupAction.get("effect").toString(); //group Effect
				colortone = groupAction.get("ct").toString(); //group Colortone
				colormode = groupAction.get("colormode").toString(); //group Colormode
				String[] color = groupAction.get("xy").toString().replace("[", "").replace("]", "").split(",");
				colorX = color[0]; //group color-X
				colorY = color[1]; //group color-Y
			}
			
			JSONObject groupState = (JSONObject) groupBuffer.get("state"); //Look inside "state" substring
			String all_on = groupState.get("all_on").toString(); //all lights on?
			String any_on = groupState.get("any_on").toString(); //any lights on?
			
			String streamActive = null;
			String streamOwner = null;
			if(type.equalsIgnoreCase("Entertainment")) {
				JSONObject groupStream = (JSONObject) groupBuffer.get("stream"); //Look inside "stream" substring
				streamActive = groupStream.get("active").toString(); //Is light stream active
				streamOwner = (String) groupStream.get("owner"); //If active, owner ID
			}
			
			newGroupList[x] = new Groups(id, name, type, state, brightness, hue, saturation, effect, colorX, colorY, colortone, alert, colormode, all_on, any_on, streamActive, streamOwner, lightArray);
		}
		return newGroupList;
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
