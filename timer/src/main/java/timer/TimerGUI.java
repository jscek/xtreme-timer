package timer;

import java.awt.*;
import java.util.List;

public class TimerGUI {


	public void displayGUI(List<TimerRecord> timerRecordList) {
		showNotification(NotifyMode.INFO,"Timer application","Application has started.");
		System.out.println("//////////Timers///////////");
		displayTimers(timerRecordList);
		System.out.println("//////////Command///////////");
		System.out.println("Create {project}\t\tStart {id}\t\tStop {id}\t\tResume {id}\t\tSave {filename}\t\tRead {filename}\t\tReport {start} {stop}\t\tRefresh\t\tQuit");
	}

	public void displayTimers(List<TimerRecord> timerRecordList) {

		System.out.printf("%-10.10s %-20.20s %-20.20s %-20.20s%n", "Id", "Project", "Duration", "IsRunning");
		timerRecordList.forEach(e -> {
			System.out.printf("%-10.10s %-20.20s %-20.20s %-20.20s%n",
					e.getId(), e.getProjectName(), displayDuration(e.getDuration().getSeconds()), e.isRunning());
		});
	}

	private String displayDuration(long duration) {
		return String.format("%d:%02d:%02d", duration / 3600, (duration % 3600) / 60, (duration % 60));
	}

	public enum NotifyMode {
		ERROR,
		INFO,
		WARNING
	}

	public void showNotification(NotifyMode mode, String title, String msg) {
		try{
			SystemTray tray = SystemTray.getSystemTray();
			Image image = Toolkit.getDefaultToolkit().createImage("some-icon.png");

			TrayIcon trayIcon = new TrayIcon(image, "Java AWT Tray Demo");
			trayIcon.setImageAutoSize(true);
			trayIcon.setToolTip("System tray icon demo");
			tray.add(trayIcon);

			switch (mode) {
				case ERROR:
					trayIcon.displayMessage(title, msg, TrayIcon.MessageType.ERROR);
					break;
				case INFO:
					trayIcon.displayMessage(title, msg, TrayIcon.MessageType.INFO);
					break;
				case WARNING:
					trayIcon.displayMessage(title, msg, TrayIcon.MessageType.WARNING);
					break;
			}

		}catch(Exception ex){
			System.err.print(ex);
		}
	}
}
