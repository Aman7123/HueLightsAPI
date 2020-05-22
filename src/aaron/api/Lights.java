package aaron.api;

public class Lights {
	private String id, state, bri, hue, sat, colorX, colorY, ct, colormode, name;
	private boolean reachable;
	private boolean color;
	
	
	public Lights(String id, String state, String brightness, String hue, String saturation, String colorX, String colorY, String colortone, String colormode, String name, boolean connected, boolean color) {
		this.id = id;
		this.state = state;
		this.bri = brightness;
		this.hue = hue;
		this.sat = saturation;
		this.colorX = colorX;
		this.colorY = colorY;
		this.ct = colortone;
		this.colormode = colormode;
		this.name = name;
		this.reachable = connected;
		this.color = color;
	}
	
	public String toString() {
		return this.id + "," +
				this.state + "," +
				this.bri + "," +
				this.hue + "," +
				this.sat + "," +
				this.colorX + "," +
				this.colorY + "," +
				this.ct + "," +
				this.colormode + "," +
				this.name + "," +
				this.reachable + "," +
				this.color;
	}
}
