package aaron.api;

import java.io.*;
import java.net.*;
import org.json.simple.*;
import org.json.simple.parser.*;

/**
 * This class-file is a HTTP curl interface that can connect with a Philips Hue Bridge on a local network to send and receive commands. 
 * Please read method documentation for more information about each part.
 * 
 * @author Aaron Renner
 * @version 05.09.2020
 * {@summary See the Hue developer portal at https://developers.meethue.com/ for more information.}
 */
public class Request {
	// 
	private String apikey, ip, apiURL;
	
	/**
	 * The constructor needs to be supplied with the Hue Bridge IP and an API key to operate.
	 * @param ip Variable stores the API Key from the Hue Bridge.
	 * @param apikey Variable store the location of the Hue Bridge on the network.
	 */
	public Request(String ip, String apikey) {
		if(apikey.length()>=32) {
			this.apikey = apikey;
			this.ip = "http://" + ip;
			apiURL = this.ip + "/api/" + this.apikey;
		} else {
			System.out.println("[ERROR] No valid API key given, input=" + apikey);
		}
	}
	
	public String sendRequest(String Object, String URL, JSONObject Data, String Method) {
		String returnData = null; //Create an empty return field.
		String requestURL = this.apiURL + URL;
		switch(Object) {
		case "Lights": {
			switch(Method) {
			case "GET":
				returnData = getData(requestURL);
				break;
			case "PUT":
				returnData = putData(requestURL, Data.toJSONString());
				break;
			case "POST":
				returnData = postData(requestURL, Data.toJSONString());
				break;
			case "DELETE":
				returnData = deleteData(requestURL, Data.toJSONString());
				break;
			}
			break;
		}
		case "Groups": {
			switch(Method) {
			case "GET":
				returnData = getData(requestURL);
				break;
			case "PUT":
				returnData = putData(requestURL, Data.toJSONString());
				break;
			case "POST":
				returnData = postData(requestURL, Data.toJSONString());
				break;
			case "DELETE":
				returnData = deleteData(requestURL, Data.toJSONString());
				break;
			}
			break;
		}
		case "Scenes": {
			switch(Method) {
			case "GET":
				returnData = getData(requestURL);
				break;
			case "PUT":
				returnData = putData(requestURL, Data.toJSONString());
				break;
			case "POST":
				returnData = postData(requestURL, Data.toJSONString());
				break;
			case "DELETE":
				returnData = deleteData(requestURL, Data.toJSONString());
				break;
			}
			break;
		}
		case "Schedules": {
			switch(Method) {
			case "GET":
				returnData = getData(requestURL);
				break;
			case "PUT":
				returnData = putData(requestURL, Data.toJSONString());
				break;
			case "POST":
				returnData = postData(requestURL, Data.toJSONString());
				break;
			case "DELETE":
				returnData = deleteData(requestURL, Data.toJSONString());
				break;
			}
			break;
		}
		}
		return returnData;
	}

	public String createAPI(String deviceName) {
		return postData(ip + "/api", "{\"devicetype\": \""+ deviceName +"\"}");
	}
	
	public String setLight(String light, String value, String key) {
		String website = ip + "/api/" + apikey + "/lights/" + light + "/state";
		String data = null;
		if(value.equals("on") || value.equals("bri") || value.equals("hue") || value.equals("sat") || value.equals("ct") ||
				value.equals("transitiontime") || value.equals("bri_inc") || value.equals("sat_inc") || value.equals("hue_inc") || value.equals("ct_inc")) {//handles boolean, and int commands to hue light
			String dataMix = "{\"" + value +"\":" + key + "}";
			data = dataMix;
		}
		else if(value.equals("name") || value.equals("alert") || value.equals("effect")) {//handles string commands to hue
			String dataMix = "{\"" + value +"\":\"" + key + "\"}";
			data = dataMix;
		}
		else if(value.equals("xy")) {
			String dataMix = "{\"" + value +"\":" + key + "}";
			data = dataMix;
		}
		return putData(website, data); //Create PUT Request
	}
	
	public String setGroup(String group, String value, String key) {
		String website = ip + "/api/" + apikey + "/groups/" + group + "/action";
		String data = null;
		if(value.equals("on") || value.equals("bri") || value.equals("hue") || value.equals("sat") || value.equals("ct") ||
			value.equals("transitiontime") || value.equals("bri_inc") || value.equals("sat_inc") || value.equals("hue_inc") || 
			value.equals("xy") || value.equals("ct_inc") || value.equals("xy_inc")) {//handles boolean, and int commands to hue bridge
			
			String dataMix = "{\"" + value +"\":" + key + "}";
			data = dataMix;
		}
		else if(value.equals("alert") || value.equals("effect") || value.equals("scene")) {//handles string commands to hue
			String dataMix = "{\"" + value +"\":\"" + key + "\"}";
			data = dataMix;
		}
		return putData(website, data); //Create PUT Request
	}
	
	public String POSTaction(String groupNumber, String data) {
		String website = ip + "/api/" + apikey + "/groups/" + groupNumber + "/action";
		return putData(website, data);
	}
	
	public String getData(String url) {
		String recievedText = null;
		try {
			URL webpage = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(webpage.openStream()));
			
			recievedText = in.readLine();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recievedText;
	}
	
	private String putData(String url, String data) {
		String responceData = null;
		try {
			URL website = new URL(url);
			HttpURLConnection httpCon = (HttpURLConnection) website.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("PUT");
			OutputStreamWriter out = new OutputStreamWriter(
			    httpCon.getOutputStream());
			out.write(data);
			out.close();
			//httpCon.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
			responceData = in.readLine();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responceData;
	}
	
	private String postData(String url, String data) {
		String responceData = null;
		try {
			URL website = new URL(url);
			HttpURLConnection httpCon = (HttpURLConnection) website.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			OutputStreamWriter out = new OutputStreamWriter(
			    httpCon.getOutputStream());
			out.write(data);
			out.close();
			//httpCon.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
			responceData = in.readLine();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responceData;
	}
	
	private String deleteData(String url, String data) {
		String responceData = null;
		try {
			URL website = new URL(url);
			HttpURLConnection httpCon = (HttpURLConnection) website.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("DELETE");
			OutputStreamWriter out = new OutputStreamWriter(
			    httpCon.getOutputStream());
			out.write(data);
			out.close();
			//httpCon.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
			responceData = in.readLine();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responceData;
	}
}
