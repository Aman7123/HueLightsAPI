package aaron.api.main;

import aaron.api.Groups;
import aaron.api.GroupsHelper;
import aaron.api.Request;

public class testCase3_Groups {
	public static Groups[] groups;

	public static void main(String[] args) {
		Request lightapi = new Request("192.168.1.2", "WtT1QWLi5FRRewFZtfvFcqDZ8C04z-m0Dn-NeSDU");
		GroupsHelper helper = new GroupsHelper(lightapi);
		groups = helper.getGroups();
		for(Groups x : groups) {
			System.out.println(x);
		}
	}

}
