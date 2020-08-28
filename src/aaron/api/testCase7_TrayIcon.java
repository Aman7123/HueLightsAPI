package aaron.api;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.json.simple.JSONObject;

public class testCase7_TrayIcon {
	public static Scenes[] scenes;
	public static Groups[] groups;
	private static String groupURL = "/groups/";

	public static void main(String[] args) throws AWTException {
		Request lightapi = new Request("192.168.1.2", "WtT1QWLi5FRRewFZtfvFcqDZ8C04z-m0Dn-NeSDU");
		ScenesHelper sceneshelper = new ScenesHelper(lightapi);
		scenes = sceneshelper.getScenes();
		
		GroupsHelper groupshelper = new GroupsHelper(lightapi);
		groups = groupshelper.getGroups();
		
		PopupMenu popup = new PopupMenu();
		for(Groups x : groups) {
			
			String groupID = x.getAttribute("id");
			String groupType = x.getAttribute("type");
			Menu fx = new Menu(x.getAttribute("name")   + " - " + groupType);
			
			for(Scenes y : scenes) {
				
				String sceneID = y.getAttribute("id");
				String yGroup = y.getAttribute("group");
				
				try {
					if(yGroup.equals(groupID)) {
						
						MenuItem yx = new MenuItem(y.getAttribute("name"));
						yx.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								String callURL = groupURL + groupID + "/action";
								JSONObject sceneData = new JSONObject();
								sceneData.put("scene", sceneID);
								lightapi.sendRequest("Groups", callURL, sceneData, "PUT");
								
							}
						});
						fx.add(yx);
					}
				} catch (NullPointerException ex) {
					//ex.printStackTrace();
					//Ignore this error
				}
			}
			popup.add(fx);
		}
		
		MenuItem exit = new MenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		popup.add(exit);
		Image image = Toolkit.getDefaultToolkit().getImage("images/small-hue-bw-icon.png");
		TrayIcon trayIcon = new TrayIcon(image, "Tray Demo", popup);
		SystemTray tray = SystemTray.getSystemTray();
		tray.add(trayIcon);
	}

}
