package timer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TimerApp {
	List<TimerRecord> timerRecordList;
	TimerGUI timerGUI = new TimerGUI();
	Scanner scanner;

	public TimerApp() {
		timerRecordList = new ArrayList<>();
		timerGUI = new TimerGUI();
		scanner = new Scanner(System.in);
	}

	public List<TimerRecord> getTimerRecords() {
		return timerRecordList;
	}

	public void start() {
		boolean flag = true;
		while (flag) {
			timerGUI.displayGUI(timerRecordList);
			String input = scanner.nextLine();
			String[] strings = input.split(" ");
			if (strings[0].equals("Create")) {
				addTimer();
			} else if (strings[0].equals("Start")) {
				startTimer(Long.valueOf(strings[1]));
			} else if (strings[0].equals("Stop")) {
				stopTimer(Long.parseLong(strings[1]));
			} else if (strings[0].equals("Resume")) {
				resumeTimer(Long.parseLong(strings[1]));
			} else if (strings[0].equals("Quit")) {
				flag = false;
			} else if (strings[0].equals("Refresh")) {

			}
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		}

	}

	public void addTimer() {
		timerRecordList.add(new TimerRecord(getUniqueId()));
	}

	private Long getUniqueId() {
		return timerRecordList.size() + 1L;
	}


	public void startTimer(Long id) {
		Optional<TimerRecord> timer = getTimerById(id);

		timer.ifPresent(TimerRecord::startTimer);

	}

	Optional<TimerRecord> getTimerById(Long id) {

		return timerRecordList.stream().filter(e -> e.getId().equals(id)).findFirst();
	}

	public void stopTimer(long id) {
		Optional<TimerRecord> timer = getTimerById(id);
		timer.ifPresent(TimerRecord::stopTimer);

	}

	public void resumeTimer(long id) {
		Optional<TimerRecord> timer = getTimerById(id);
		timer.ifPresent(TimerRecord::resume);
	}
}
