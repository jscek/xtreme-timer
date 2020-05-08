package timer;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TimerApp {
	List<TimerRecord> timerRecordList;
	TimerGUI timerGUI = new TimerGUI();
	Scanner scanner;
	TimerReport timerReport;

	public TimerApp() {
		timerRecordList = new ArrayList<>();
		timerGUI = new TimerGUI();
		scanner = new Scanner(System.in);
		timerReport = new TimerReport();
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

			}else if (strings[0].equals("Report")) {
				if(strings.length==1){
					createReport(null,null);
				}else{
					LocalDate date = LocalDate.parse(strings[1]);
					Instant start = date.atStartOfDay(ZoneId.of("Europe/Paris")).toInstant();
					LocalDate date2 = LocalDate.parse(strings[2]);
					Instant stop = date2.atStartOfDay(ZoneId.of("Europe/Paris")).toInstant();
					createReport(start,stop);
				}
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

	public String createReport(Instant start, Instant stop){

		if (start==null)
			start = Instant.parse("2018-11-30T18:35:24.00Z");
		if (stop==null)
			stop = Instant.parse("9999-11-30T18:35:24.00Z");

		String filename = "rep.csv";
		timerReport.saveReport(filename,timerReport.createReportContent(start, stop, (ArrayList) timerRecordList));

		return filename;
	}
}
