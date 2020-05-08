package timer;

import java.time.Instant;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TimerApp {
	private List<TimerRecord> timerRecordList;
	private TimerGUI timerGUI;
	private TimerSaver saver;
	private TimerLoader loader;
	private Scanner scanner;
	private boolean shouldFinish;
	private TimerReport timerReport;

	public TimerApp() {
		timerRecordList = new ArrayList<>();
		timerGUI = new TimerGUI();
		saver = new TimerSaver();
		loader = new TimerLoader();
		scanner = new Scanner(System.in);
		timerReport = new TimerReport();
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
				addTimer(input);
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
				break;
			case "read":
				loadTimerRecords(input[1]);
				break;
			case "quit":
				shouldFinish = true;
				break;
			case "report":
				if (input.length == 1) {
					createReport(null,null);
				} else {
					LocalDate date = LocalDate.parse(input[1]);
					Instant start = date.atStartOfDay(ZoneId.of("Europe/Paris")).toInstant();
					LocalDate date2 = LocalDate.parse(input[2]);
					Instant stop = date2.atStartOfDay(ZoneId.of("Europe/Paris")).toInstant();
					createReport(start, stop);
				}
				break;
			case "sendemail":
				if (input.length == 1) {
					SendEmail.send("extremetimerPE2020@wp.pl", "Test3", "This is the report", createReport(null, null));
				} else if (input.length == 4) {
					SendEmail.send(input[1], input[2], input[3], createReport(null, null));
				}
				else {
					System.out.println("Wrong parameters provided");
				}
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

	public void addTimer(String[] input) {
		if (input.length >= 2) {
			timerRecordList.add(new TimerRecord(getUniqueId(), input[1]));
		} else {
			addTimer();
		}
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

	public String createReport(Instant start, Instant stop) {
		if (start == null)
			start = Instant.parse("2018-11-30T18:35:24.00Z");
		if (stop == null)
			stop = Instant.parse("9999-11-30T18:35:24.00Z");

		String filename = "rep.csv";
		timerReport.saveReport(filename, timerReport.createReportContent(start, stop, (ArrayList) timerRecordList));

		return filename;
	}

	public void saveTimerRecords(String filename) {
		try {
			saver.saveToFile(timerRecordList, filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadTimerRecords(String filename) {
		timerRecordList = loader.loadFromFile(filename);
	}

	private void clearConsole() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
}