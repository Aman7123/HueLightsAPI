package aaron.api.main;

import aaron.api.Request;
import aaron.api.Scenes;
import aaron.api.ScenesHelper;

public class testCase4_Scenes {
	public static Scenes[] scenes;

	public static void main(String[] args) {
		Request lightapi = new Request("192.168.1.2", "WtT1QWLi5FRRewFZtfvFcqDZ8C04z-m0Dn-NeSDU");
		ScenesHelper helper = new ScenesHelper(lightapi);
		scenes = helper.getScenes();
		for(Scenes x : scenes) {
			System.out.println(x);
		}
	}

}
