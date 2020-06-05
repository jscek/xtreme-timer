package timer.GUI;

import java.util.List;

import timer.TimerRecord;
import timer.enums.NotifyMode;
import timer.notification.NotificationGUI;
import timer.notification.NotificationGUIInterface;

public abstract class TimerGUI {

	public TimerGUI() {
	}

	public abstract void display(List<TimerRecord> timerRecordList);

	public String displayDuration(long duration) {
		return String.format("%d:%02d:%02d", duration / 3600, (duration % 3600) / 60, (duration % 60));
	}

}
