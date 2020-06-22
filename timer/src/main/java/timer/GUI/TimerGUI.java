package timer.GUI;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import timer.actions.Actions;
import timer.base.TimerRecord;

public abstract class TimerGUI {

	public TimerGUI() {
	}

	public abstract void display(List<TimerRecord> timerRecordList, Actions actionsChain);

	public String displayDuration(long duration) {
		return String.format("%d:%02d:%02d", duration / 3600, (duration % 3600) / 60, (duration % 60));
	}

	public String displayDates(Instant date) {
		if (date == null) {
			return "---";
		} else {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			return formatter.format(Date.from(date));
		}
	}

}
