package timer;

import java.time.Instant;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.time.Duration;

import timer.enums.NotifyMode;
import timer.notification.NotificationGUI;
import timer.notification.NotificationGUIInterface;
import timer.Actions;

public class TimerApp {
	private List<TimerRecord> timerRecordList;
	public boolean shouldFinish;

	private final Map<TimerRecord, TimerTask> recordNotifications;
	private final Timer scheduler;
	private final TimerSaver saver;
	private final TimerLoader loader;
	private final Scanner scanner;
	private final TimerReport timerReport;
	private final NotificationGUIInterface notificationGUI;
	private final Actions actions;
	private final TimersGUI timersGUI;
	private final GUI GUI;

	public TimerApp() {
		timerRecordList = new ArrayList<>();
		recordNotifications = new HashMap<>();
		scheduler = new Timer();
		saver = new TimerSaver();
		loader = new TimerLoader();
		scanner = new Scanner(System.in);
		timerReport = new TimerReport();
		shouldFinish = false;
		notificationGUI = new NotificationGUI();
		actions = new Actions();
		timersGUI = new TimersGUI();
		GUI = new GUI();
	}

	public List<TimerRecord> getTimerRecords() {
		return timerRecordList;
	}

	public void start() {
		while (!shouldFinish) {
			GUI.display(timerRecordList);
			String[] input = getAndParseInput();
			actions.perform(this, input);
			clearConsole();
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

		if (timer.isPresent()) {
			timer.get().startTimer();
			registerNotification(timer.get());
		}
	}

	Optional<TimerRecord> getTimerById(Long id) {

		return timerRecordList.stream().filter(e -> e.getId().equals(id)).findFirst();
	}

	public void stopTimer(long id) {
		Optional<TimerRecord> timer = getTimerById(id);

		if (timer.isPresent()) {
			timer.get().stopTimer();
			recordNotifications.get(timer.get()).cancel();
		}
	}

	public void resumeTimer(long id) {
		Optional<TimerRecord> timer = getTimerById(id);
		if (timer.isPresent()) {
			timer.get().resume();
			registerNotification(timer.get());
		}
	}

	public void setLimit(long id, Duration limit) {
		Optional<TimerRecord> timer = getTimerById(id);
		timer.ifPresent(record -> record.setLimit(limit));
	}

	public String createReport(Instant start, Instant stop, String filename) {
		if (start == null) {
			start = Instant.parse("2018-11-30T18:35:24.00Z");
		}
		if (stop == null) {
			stop = Instant.parse("9999-11-30T18:35:24.00Z");
		}

		filename += ".csv";

		timerReport.saveReport(filename, start, stop, new ArrayList(timerRecordList));

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

	private void registerNotification(TimerRecord timerRecord) {
		long limit = timerRecord.getLimit().getSeconds();
		Instant startTime = timerRecord.getStartTime();
		Instant limitTime = startTime.plusSeconds(limit);

		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				notificationGUI.showNotification(NotifyMode.WARNING, "Time limit", "Limit for " + timerRecord.getProjectName() + " has been exceeded.");
			}
		};

		recordNotifications.put(timerRecord, task);
		scheduler.schedule(task, Date.from(limitTime));
	}
}
