package timer.GUI;

import timer.actions.Actions;
import timer.base.TimerRecord;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class TimersGUI extends TimerGUI {

	@Override
	public void display(List<TimerRecord> timerRecordList, Actions actionsChain) {

		AsciiTable timers = new AsciiTable();

		timers.addRule();
		timers.addRow(null, null, null, null, null, null, "TIMERS").setTextAlignment(TextAlignment.CENTER);
		timers.addRule();
		timers.addRow("ID", "PROJECT", "DURATION", "ISRUNNING", "LIMIT", "STARTED", "UPDATE").setTextAlignment(TextAlignment.CENTER);
		timers.addRule();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		timerRecordList.forEach(e -> {
			try {
				timers.addRow(e.getId(), e.getProjectName(), displayDuration(e.getDuration().getSeconds()), e.isRunning(), displayDuration(e.getLimit().getSeconds()), formatter.format(Date.from(e.getStartTime())), formatter.format(Date.from(e.getStopTime())));
				timers.addRule();
			} catch (Exception ee) {
				timers.addRow(null, null, null, null, null, null, "OMG").setTextAlignment(TextAlignment.CENTER);
				timers.addRule();
			}
		});
		System.out.printf(timers.render());
	}
}
