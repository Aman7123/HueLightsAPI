package aaron.api;

import java.util.Arrays;

public class Scenes {

	private String id, name, type, group, locked, appdata_version, appdata_data, picture, image, lastupdated, version;
	private String[] lights;
	
	
	public Scenes(String id, String name, String type, String group, String locked, String appdata_version, String appdata_data, String picture, String image, String lastupdated, String version, String[] lightArray) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.group = group;
		this.locked = locked;
		this.appdata_version = appdata_version;
		this.appdata_data = appdata_data;
		this.picture = picture;
		this.image = image;
		this.lastupdated = lastupdated;
		this.version = version;
		this.lights = lightArray;
	}
	
	public String toString() {
		return this.id + "," +
				this.name + "," +
				this.type + "," +
				this.group + "," +
				this.locked + "," +
				this.appdata_version + "," +
				this.appdata_data + "," +
				this.picture + "," +
				this.image + "," +
				this.lastupdated + "," +
				this.version + "," +
				Arrays.deepToString(lights);
	}
	
	public String getAttribute(String variable) {
		String toReturn = null;
		switch(variable) {
			case "id":
				toReturn = this.id;
				break;
			case "name":
				toReturn = this.name;
				break;
			case "type":
				toReturn = this.type;
				break;
			case "group":
				toReturn = this.group;
				break;
			case "locked":
				toReturn = this.locked;
				break;
			case "appdata_version":
				toReturn = this.appdata_version;
				break;
			case "appdata_data":
				toReturn = this.appdata_data;
				break;
			case "picture":
				toReturn = this.picture;
				break;
			case "image":
				toReturn = this.image;
				break;
			case "lastupdated":
				toReturn = this.lastupdated;
				break;
			case "version":
				toReturn = this.version;
				break;
		}
		return toReturn;
	}
}
