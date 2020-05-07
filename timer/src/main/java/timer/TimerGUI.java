package timer;

import java.time.Duration;
import java.util.List;

public class TimerGUI {


	public void displayGUI(List<TimerRecord> timerRecordList) {
		System.out.println("//////////Timers///////////");
		displayTimers(timerRecordList);
		System.out.println("//////////Command///////////");
		System.out.println("Create {project}\t\tStart {id}\t\tStop{id}\t\tResume{id}\t\tSave {filename}\t\tRead {filename}\t\tRefresh\t\tQuit");

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
}
