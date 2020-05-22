package aaron.api;

public class testCase5_Schedules {
	public static Schedules[] schedules;

	public static void main(String[] args) {
		Request lightapi = new Request("192.168.1.2", "WtT1QWLi5FRRewFZtfvFcqDZ8C04z-m0Dn-NeSDU");
		SchedulesHelper helper = new SchedulesHelper(lightapi);
		schedules = helper.getSchedules();
		for(Schedules x : schedules) {
			System.out.println(x);
		}
	}

}
