package timer;

import java.time.Duration;
import java.util.List;

public class TimerGUI {


	public void displayGUI(List<TimerRecord> timerRecordList) {
		System.out.println("//////////Timers///////////");
		displayTimers(timerRecordList);
		System.out.println("//////////Command///////////");
		System.out.println("Create\tStart {id}\tStop{id} \tResume{id} \tQuit \tRefresh");

	}

	public void displayTimers(List<TimerRecord> timerRecordList) {

		System.out.println("Id\t\tStartTime\t\tEndTime\t\tDuration\t\tIsRunning");
		timerRecordList.forEach(e -> {
			System.out.println(e.getId() + "\t" + e.getStartTime() + "\t" + e.getStopTime() + "\t"
					+ displayDuration(e.getDuration().getSeconds()) + "\t" + e.isRunning());
		});

	}
	private String displayDuration(long duration) {
		return String.format("%d:%02d:%02d", duration / 3600, (duration % 3600) / 60, (duration % 60));
	}
}
