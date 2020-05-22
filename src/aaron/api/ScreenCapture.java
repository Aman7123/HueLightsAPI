package aaron.api;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenCapture {
	
	private Color screenColor;
	
	public ScreenCapture() {
		this.screenColor = averageColor(getScreen());
		double[] xyColors = getRGBtoXY(screenColor);
		System.out.println(xyColors[0] + ", " + xyColors[1]);
	}
	
	private Color averageColor(BufferedImage bi) {
		long redBucket = 0;
		long greenBucket = 0;
		long blueBucket = 0;
		long pixelCount = 0;

		for (int y = 0; y < bi.getHeight(); y++) {
			for (int x = 0; x < bi.getWidth(); x++) {
				Color c = new Color(bi.getRGB(x, y));

				pixelCount++;
				redBucket += c.getRed();
				greenBucket += c.getGreen();
				blueBucket += c.getBlue();
				// does alpha matter?
			}
		}
		int red = Integer.valueOf(new String().valueOf(redBucket / pixelCount));
		int green = Integer.valueOf(new String().valueOf(greenBucket / pixelCount));
		int blue = Integer.valueOf(new String().valueOf(blueBucket / pixelCount));
		
		return new Color(red, green, blue);
	}
	
	private BufferedImage getScreen() {
		BufferedImage newCapture = null;
		try {
			Robot r = new Robot();
			Rectangle screenRect = new Rectangle(0, 0, 0, 0);
			for (GraphicsDevice gd : GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()) {
			    screenRect = screenRect.union(gd.getDefaultConfiguration().getBounds());
			}
			newCapture = r.createScreenCapture(screenRect);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return newCapture;
	}
	
	/**
	 * This method turns a Java Color Object into a XY color represented in the CIE Color space.
	 * This method was created by the user "tim" on a stackoverflow form post https://stackoverflow.com/a/22649803
	 * 
	 * @param c The Java Color object of desired color.
	 * @return A double containing the CIE XY color space value.
	 */
	public double[] getRGBtoXY(Color c) {
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
