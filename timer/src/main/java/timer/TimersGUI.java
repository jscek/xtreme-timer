package timer;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

import java.util.List;


public class TimersGUI extends TimerGUI {

    @Override
    public void display(List<TimerRecord> timerRecordList) {

        AsciiTable timers = new AsciiTable();

        timers.addRule();
        timers.addRow(null, null, null, null, "TIMERS").setTextAlignment(TextAlignment.CENTER);
        timers.addRule();
        timers.addRow("ID", "PROJECT", "DURATION", "ISRUNNING", "LIMIT [s]" ).setTextAlignment(TextAlignment.CENTER);
        timers.addRule();

        timerRecordList.forEach(e -> {
            try{
                timers.addRow(e.getId(), e.getProjectName(), displayDuration(e.getDuration().getSeconds()), e.isRunning(), e.getLimit().getSeconds());
                timers.addRule();
            } catch (Exception ee){
                timers.addRow(null, null, null, null, "OMG").setTextAlignment(TextAlignment.CENTER);
                timers.addRule();
            }
        });
        System.out.printf(timers.render());
    }
}
