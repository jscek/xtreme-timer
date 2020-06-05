package timer.GUI;

import timer.GUI.TimerGUI;
import timer.TimerRecord;

import java.util.List;


public class TimersGUI extends TimerGUI {

    @Override
    public void display(List<TimerRecord> timerRecordList) {
        System.out.printf("%-10.10s %-20.20s %-20.20s %-20.20s %-20.20s%n", "Id", "Project", "Duration", "IsRunning", "Limit [s]");
        timerRecordList.forEach(e -> {
            System.out.printf("%-10.10s %-20.20s %-20.20s %-20.20s %-20.20s%n",
                    e.getId(), e.getProjectName(), displayDuration(e.getDuration().getSeconds()), e.isRunning(), e.getLimit().getSeconds());
        });
    }
}
