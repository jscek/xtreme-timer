package timer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TimerApp {
	private List<TimerRecord> timerRecordList;
	private TimerGUI timerGUI;
	private TimerSaver saver;
	private Scanner scanner;
	private boolean shouldFinish;

	public TimerApp() {
		timerRecordList = new ArrayList<>();
		timerGUI = new TimerGUI();
		saver = new TimerSaver();
		scanner = new Scanner(System.in);
		shouldFinish = false;
	}

	public List<TimerRecord> getTimerRecords() {
		return timerRecordList;
	}

	public void start() {
		while (!shouldFinish) {
			timerGUI.displayGUI(timerRecordList);
			String[] input = getAndParseInput();
			performAction(input);
			clearConsole();
		}
	}


	private void performAction(String[] input) {
		switch (input[0].toLowerCase()) {
			case "create":
				addTimer();
				break;
			case "start":
				startTimer(Long.valueOf(input[1]));
				break;
			case "stop":
				stopTimer(Long.parseLong(input[1]));
				break;
			case "resume":
				resumeTimer(Long.parseLong(input[1]));
				break;
			case "save":
				saveTimerRecords(input[1]);
			case "quit":
				shouldFinish = true;
				break;
			case "refresh":

				break;
		}
	}

	private String[] getAndParseInput() {
		return scanner
				.nextLine()
				.split(" ");
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

	public void saveTimerRecords(String filename) {
		try {
			saver.saveToFile(timerRecordList, filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void clearConsole() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
}
