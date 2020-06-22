package timer.base;

import java.time.Instant;
import java.io.IOException;
import java.util.*;
import java.time.Duration;
import java.util.stream.Collectors;

import timer.GUI.GUI;
import timer.actions.*;
import timer.enums.NotifyMode;
import timer.notification.NotificationGUI;
import timer.notification.NotificationGUIInterface;
import timer.report.DailyReportGenerator;
import timer.report.TimerReport;
import timer.utils.PropertiesReader;

public class TimerCommandLineApp extends TimerApp {
	private List<TimerRecord> timerRecordList;
	public boolean shouldFinish;

	private final Map<TimerRecord, TimerTask> recordNotifications;
	private final Timer scheduler;
	private final TimerSaver saver;
	private final TimerLoader loader;
	private final Scanner scanner;
	private final TimerReport timerSummaryReport;
	private final DailyReportGenerator timerDailyReport;
	private final NotificationGUIInterface notificationGUI;
	public Actions actionChain;

	private final GUI GUI;

	public TimerCommandLineApp() {
		timerRecordList = new ArrayList<>();
		recordNotifications = new HashMap<>();
		scheduler = new Timer();
		saver = new TimerSaver();
		loader = new TimerLoader();
		scanner = new Scanner(System.in);
		timerSummaryReport = new timer.report.SummaryReport();
		timerDailyReport = new DailyReportGenerator();
		shouldFinish = false;
		notificationGUI = new NotificationGUI();
		actionChain = getActionsChain();
		GUI = new GUI();
	}

	private Actions getActionsChain() {

		List<Actions> actionsList = new ArrayList<>();
		Actions create = new Create();

		actionsList.add(create);
		actionsList.add(new Start());
		actionsList.add(new Stop());
		actionsList.add(new Read());
		actionsList.add(new Quit());
		actionsList.add(new Refresh());
		actionsList.add(new SummaryReport());
		actionsList.add(new DailyReport());
		actionsList.add(new Resume());
		actionsList.add(new Save());
		actionsList.add(new SendEmail());
		actionsList.add(new SetLimit());

		return  create.chainActions(actionsList);
	}

	public void start() {
		loadRecordsFromStorage();
		while (!shouldFinish) {
			GUI.display(timerRecordList, actionChain);
			String[] input = getAndParseInput();
			try {
				actionChain.exec(this, input );
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				System.out.println("Invalid command: " + Arrays.toString(input));
				String[] def = {"Refresh"};
			}
		}
	}

	@Override
	public void finish() {
		shouldFinish = true;
	}

	private void loadRecordsFromStorage() {
		String filename = PropertiesReader.getInstance().getProperty("storage", "");
		loadTimerRecords(filename);
	}

	private String[] getAndParseInput() {
		return scanner
				.nextLine()
				.split(" ");
	}

	public void createTimer(String projectName) {
		timerRecordList.add(new TimerRecord(getUniqueId(), projectName));
	}

	private Long getUniqueId() {
		return timerRecordList.size() + 1L;
	}

	public void startTimer(long id) {
		Optional<TimerRecord> timer = getTimerById(id);

		if (timer.isPresent()) {
			timer.get().startTimer();
			registerNotification(timer.get());
		}
	}

	public Optional<TimerRecord> getTimerById(Long id) {
		return timerRecordList.stream().filter(e -> e.getId().equals(id)).findFirst();
	}

	public void stopTimer(long id) {
		Optional<TimerRecord> timer = getTimerById(id);

		if (timer.isPresent()) {
			timer.get().stopTimer();
			if (recordNotifications.containsKey(timer.get())) {
				recordNotifications.get(timer.get()).cancel();
			}
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

	public String createSummaryReport(Instant start, Instant stop, String filename) {
		List<TimerRecord> tempList;
		if (start != null && stop != null) {
			tempList = timerRecordList.stream()
					.filter(record -> record.isBetween(start, stop))
					.collect(Collectors.toList());
		}else{
			tempList = timerRecordList;
		}

		timerSummaryReport.saveReport(filename, tempList);

		return filename;
	}

	public String createDailyReport(long id, String filename) {
		Optional<TimerRecord> record = getTimerById(id);
		record.ifPresent(timerRecord -> timerDailyReport.saveReport(filename, timerRecord));
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

	public List<TimerRecord> getTimerRecords() {
		return timerRecordList;
	}
}
