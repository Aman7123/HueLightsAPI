package aaron.api;

public class Schedules {

	private String id, name, description, command_address, command_body, command_method, localtime, time, created, starttime, status, autodelete, recycle;
	private String[] lights;
	
	
	public Schedules(String id, String name, String description, String command_address, String command_body, String command_method, String localtime, String time, String created, String starttime, String status, String autodelete, String recycle) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.command_address = command_address;
		this.command_body = command_body;
		this.command_method = command_method;
		this.localtime = localtime;
		this.time = time;
		this.created = created;
		this.starttime = starttime;
		this.status = status;
		this.autodelete = autodelete;
		this.recycle = recycle;
	}
	
	public String toString() {
		return this.id + "," +
				this.name + "," +
				this.description + "," +
				this.command_address + "," +
				this.command_body + "," +
				this.command_method + "," +
				this.localtime + "," +
				this.time + "," +
				this.created + "," +
				this.starttime + "," +
				this.status + "," +
				this.autodelete + "," +
				this.recycle;
	}
}
