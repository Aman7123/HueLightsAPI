package aaron.api.main;

import org.json.simple.JSONObject;

import aaron.api.Lights;
import aaron.api.Request;

public class testCase2_Timer {
	public static Lights[] lights;
	private static String lightsCommand = "/groups/0/action";

	public static void main(String[] args) throws InterruptedException {
		Request lightapi = new Request("192.168.1.2", "WtT1QWLi5FRRewFZtfvFcqDZ8C04z-m0Dn-NeSDU");
		int TimerSetup_Minutes = 20;
		TimerProgram timer= new TimerProgram(TimerSetup_Minutes);
		while(true) {
			long count = timer.getCount();
			long buffer = 0;
			if(buffer<count) {
				JSONObject timerCommand = new JSONObject();
				timerCommand.put("alert", "lselect");
				String cmd = lightapi.sendRequest("Groups", lightsCommand, timerCommand, "PUT");
				buffer++;
				System.out.println(cmd);
				/**
				Thread.sleep(100);
				JSONObject timerStopCommand = new JSONObject();
				timerStopCommand.put("alert", "none");
				String spcmd = lightapi.sendRequest("Groups", lightsCommand, timerStopCommand, "PUT");
				System.out.println(spcmd);\
				*/
			}
			Thread.sleep(TimerSetup_Minutes*60000);
		}
	}

}
