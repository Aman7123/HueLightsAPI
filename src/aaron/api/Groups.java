package aaron.api;

import java.util.Arrays;

public class Groups {
	
	private String id, name, type, state, bri, hue, sat, effect, colorX, colorY, ct, alert, colormode, all_on, any_on, streamActive, streamOwner;
	private String[] lights;
	
	
	public Groups(String id, String name, String type, String state, String brightness, String hue, String saturation, String effect, String colorX, String colorY, String colortone, String alert, String colormode, String all_on, String any_on, String streamActive, String streamOwner, String[] lightArray) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.state = state;
		this.bri = brightness;
		this.hue = hue;
		this.sat = saturation;
		this.effect = effect;
		this.colorX = colorX;
		this.colorY = colorY;
		this.ct = colortone;
		this.alert = alert;
		this.colormode = colormode;
		this.all_on = all_on;
		this.any_on = any_on;
		this.streamActive = streamActive;
		this.streamOwner = streamOwner;
		this.lights = lightArray;
	}
	
	public String toString() {
		return this.id + "," +
				this.name + "," +
				this.type + "," +
				this.state + "," +
				this.bri + "," +
				this.hue + "," +
				this.sat + "," +
				this.effect + "," +
				this.colorX + "," +
				this.colorY + "," +
				this.ct + "," +
				this.alert + "," +
				this.colormode + "," +
				this.all_on + "," +
				this.any_on + "," +
				this.streamActive + "," +
				this.streamOwner + "," +
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
			case "state":
				toReturn = this.state;
				break;
			case "bri":
				toReturn = this.bri;
				break;
			case "hue":
				toReturn = this.hue;
				break;
			case "sat":
				toReturn = this.sat;
				break;
			case "effect":
				toReturn = this.effect;
				break;
			case "colorX":
				toReturn = this.colorX;
				break;
			case "colorY":
				toReturn = this.colorY;
				break;
			case "ct":
				toReturn = this.ct;
				break;
			case "alert":
				toReturn = this.alert;
				break;
			case "colormode":
				toReturn = this.colormode;
				break;
			case "all_on":
				toReturn = this.all_on;
				break;
			case "any_on":
				toReturn = this.any_on;
				break;
			case "streamActive":
				toReturn = this.streamActive;
				break;
			case "streamOwner":
				toReturn = this.streamOwner;
				break;
		}
		return toReturn;
	}

}
