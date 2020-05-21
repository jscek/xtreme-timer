package timer;

import java.util.List;

import timer.enums.NotifyMode;
import timer.notification.NotificationGUI;
import timer.notification.NotificationGUIInterface;

public class TimerGUI {

	private NotificationGUIInterface notificationGUI;

	public TimerGUI() {
		notificationGUI = new NotificationGUI();
	}

	public void displayGUI(List<TimerRecord> timerRecordList) {
		notificationGUI.showNotification(NotifyMode.INFO, "Timer application", "Application has started.");
		System.out.println("//////////Timers///////////");
		displayTimers(timerRecordList);
		System.out.println("//////////Command///////////");
		System.out.println("Create {project}\t\tStart {id}\t\tStop {id}\t\tResume {id}\t\tSetLimit {id} {duration[s]}\t\tSave {filename}\t\tRead {filename}\t\tReport {start} {stop}}\t\tSendEmail {to} {subject} {text}\t\tRefresh\t\tQuit");
	}

	public void displayTimers(List<TimerRecord> timerRecordList) {

		System.out.printf("%-10.10s %-20.20s %-20.20s %-20.20s %-20.20s%n", "Id", "Project", "Duration", "IsRunning", "Limit [s]");
		timerRecordList.forEach(e -> {
			System.out.printf("%-10.10s %-20.20s %-20.20s %-20.20s %-20.20s%n",
					e.getId(), e.getProjectName(), displayDuration(e.getDuration().getSeconds()), e.isRunning(), e.getLimit().getSeconds());
		});
	}

	private String displayDuration(long duration) {
		return String.format("%d:%02d:%02d", duration / 3600, (duration % 3600) / 60, (duration % 60));
	}


}
