package aaron.api;

import java.util.Set;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class SchedulesHelper {
	private Schedules[] schedules;
	private Request lightapi;
	private String scheduleBaseURL = "/schedules";
	private String scheduleCallURL = "/schedules/";

	public SchedulesHelper(Request apiConnection) {
		this.lightapi = apiConnection;
		this.schedules = populateSchedules();
	}
	
	public Schedules[] getSchedules() {
		return this.schedules;
	}
	
	public void updateSchedules() {
		this.schedules = populateSchedules();
	}
	
	public Schedules[] populateSchedules() {
		Schedules[] newScheduleList = null;
		JSONObject scheduleList = parseJSON(lightapi.sendRequest("Schedules", scheduleBaseURL, new JSONObject(), "GET"));
		int runCount = scheduleList.size();
		newScheduleList = new Schedules[runCount];
		
		/*This next bit of code finds the ID of the item entries in the Philips Hue API json response. 
		 */
		Set<String> scheduleMap = scheduleList.keySet();
		String[] scheduleID = new String[scheduleMap.size()];
		int bufr = 0;
		for(String x: scheduleMap) {
			scheduleID[bufr++] = x;
		}
		
		for(int x = 1; x<=runCount; x++) {
			String id = scheduleID[x-1]; //Schedule ID
			JSONObject scheduleBuffer = (JSONObject) scheduleList.get(id); //Looks inside the schedule ID
			String name = scheduleBuffer.get("name").toString(); //Schedule Name
			String description = scheduleBuffer.get("description").toString(); //Schedule Type
			String localtime = null; //Schedule Locked
			String time = scheduleBuffer.get("time").toString(); //Schedule Picture
			String created = scheduleBuffer.get("created").toString(); //Schedule Last Updated
			String status = scheduleBuffer.get("status").toString(); //Schedule Version
			String autodelete = null; //Schedule Last Updated
			String starttime = null; //Schedule Version
			String recycle = scheduleBuffer.get("recycle").toString(); //Schedule Version
			
			try {
				starttime = scheduleBuffer.get("starttime").toString();
				autodelete = scheduleBuffer.get("created").toString();
				localtime = scheduleBuffer.get("localtime").toString();
			} catch (NullPointerException nullPoint) {
				
			}

			JSONObject scheduleCommand = (JSONObject) scheduleBuffer.get("command"); //Look inside "command" substring
			String command_address = scheduleCommand.get("address").toString(); //Schedule Last Updated
			String command_body = scheduleCommand.get("body").toString(); //Schedule Version
			String command_method = scheduleCommand.get("method").toString(); //Schedule Version
			
			newScheduleList[x-1] = new Schedules(id, name, description, command_address, command_body, command_method, localtime, time, created, starttime, status, autodelete, recycle);
			
		}
		return newScheduleList;
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
