package aaron.api;

import java.awt.Color;
import java.util.*;

import org.json.simple.JSONObject;
/**
 * For more info visit: 
 * http://hyperphysics.phy-astr.gsu.edu/hbase/vision/cie.html#c4
 * http://hyperphysics.phy-astr.gsu.edu/hbase/vision/cie.html
 * @author Aaron
 *
 */
public class testCase6_Color {
	public static Schedules[] schedules;

	public static void main(String[] args) throws InterruptedException {
		Request lightapi = new Request("192.168.1.2", "WtT1QWLi5FRRewFZtfvFcqDZ8C04z-m0Dn-NeSDU");
		Color red = new Color(216, 119, 51); //Create color
		Color green = new Color(185, 214, 4); //Create color
		Color blue = new Color(88, 121, 191); //Create color
		
		double[] redXY = getRGBtoXY(red); //use method to turn RGB into CIE color
		double[] greenXY = getRGBtoXY(green); //use method to turn RGB into CIE color
		double[] blueXY = getRGBtoXY(blue); //use method to turn RGB into CIE color
		
		System.out.println("red: " + redXY[0] + ", " + redXY[1]); //Prints out some info
		System.out.println("green: " + greenXY[0] + ", " + greenXY[1]); //Prints out some info
		System.out.println("blue: " + blueXY[0] + ", " + blueXY[1]); //Prints out some info
		
		while(true) {
			lightapi.setGroup("10", "xy", "[" +redXY[0]+ "," +redXY[1]+ "]"); //Set light red
	
			double bufferX = blueXY[0] - redXY[0];
			double bufferY = blueXY[1] - redXY[1];
			System.out.println("difference from red to blue:" + bufferX + ", " + bufferY);
			
			JSONObject message = new JSONObject();
			int time = 70;
			message.put("transitiontime", time);
			List a = Arrays.asList(bufferX, bufferY);
			message.put("xy_inc", a );
			
			String result = lightapi.POSTaction("10", message.toJSONString());
			System.out.println(result);
			
			float bufferX1 = (float) (greenXY[0] - blueXY[0]);
			float bufferY1 = (float) (greenXY[1] - blueXY[1]);
			System.out.println("difference from blue to green:" + bufferX1 + ", " + bufferY1);
			
			Thread.sleep(6000);
			
			JSONObject message1 = new JSONObject();
			int time1 = 70;
			message1.put("transitiontime", time1);
			List b = Arrays.asList(bufferX1, bufferY1);
			message1.put("xy_inc", b );
			
			String result1 = lightapi.POSTaction("10", message1.toJSONString());
			System.out.println(result1);
			
			float bufferX2 = (float) (redXY[0] - greenXY[0]);
			float bufferY2 = (float) (redXY[1] - greenXY[1]);
			System.out.println("difference from green to red:" + bufferX2 + ", " + bufferY2);
			
			Thread.sleep(6000);
			
			JSONObject message2 = new JSONObject();
			int time2 = 70;
			message2.put("transitiontime", time2);
			List c = Arrays.asList(bufferX2, bufferY2);
			message2.put("xy_inc", c );
			
			String result2 = lightapi.POSTaction("10", message2.toJSONString());
			System.out.println(result2);
			
			Thread.sleep(6000);
		}
	}
	
	/**
	 * This method turns a Java Color Object into a XY color represented in the CIE Color space.
	 * This method was created by the user "tim" on a stackoverflow form post https://stackoverflow.com/a/22649803
	 * 
	 * @param c The Java Color object of desired color.
	 * @return A double containing the CIE XY color space value.
	 */
	public static double[] getRGBtoXY(Color c) {
	    // For the hue bulb the corners of the triangle are:
	    // -Red: 0.675, 0.322
	    // -Green: 0.4091, 0.518
	    // -Blue: 0.167, 0.04
	    double[] normalizedToOne = new double[3];
	    float cred, cgreen, cblue;
	    cred = c.getRed();
	    cgreen = c.getGreen();
	    cblue = c.getBlue();
	    normalizedToOne[0] = (cred / 255);
	    normalizedToOne[1] = (cgreen / 255);
	    normalizedToOne[2] = (cblue / 255);
	    float red, green, blue;

	    // Make red more vivid
	    if (normalizedToOne[0] > 0.04045) {
	        red = (float) Math.pow(
	                (normalizedToOne[0] + 0.055) / (1.0 + 0.055), 2.4);
	    } else {
	        red = (float) (normalizedToOne[0] / 12.92);
	    }

	    // Make green more vivid
	    if (normalizedToOne[1] > 0.04045) {
	        green = (float) Math.pow((normalizedToOne[1] + 0.055)
	                / (1.0 + 0.055), 2.4);
	    } else {
	        green = (float) (normalizedToOne[1] / 12.92);
	    }

	    // Make blue more vivid
	    if (normalizedToOne[2] > 0.04045) {
	        blue = (float) Math.pow((normalizedToOne[2] + 0.055)
	                / (1.0 + 0.055), 2.4);
	    } else {
	        blue = (float) (normalizedToOne[2] / 12.92);
	    }

	    float X = (float) (red * 0.649926 + green * 0.103455 + blue * 0.197109);
	    float Y = (float) (red * 0.234327 + green * 0.743075 + blue * 0.022598);
	    float Z = (float) (red * 0.0000000 + green * 0.053077 + blue * 1.035763);

	    float x = X / (X + Y + Z);
	    float y = Y / (X + Y + Z);

	    double[] xy = new double[2];
	    xy[0] = x;
	    xy[1] = y;
	    return xy;
	}

}
