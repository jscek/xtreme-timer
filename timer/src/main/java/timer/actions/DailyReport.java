package timer.actions;

import timer.base.TimerApp;

import java.time.Instant;
import java.time.LocalDate;
import java.util.TimeZone;

public class DailyReport extends Actions{

    public DailyReport() {
        this.action = this.getClass().getSimpleName().toLowerCase();
        this.help = "DailyReport {id} {filename}";
    }

    @Override
    protected void perform(String[] input, TimerApp app) {
        createReport(app, input);
    }

    private void createReport(TimerApp app, String[] input) {
        try {
            long id = Long.parseLong(input[1]);
            app.createDailyReport(id, input[2]);
        } catch (Exception e) {
            //Do nothing
        }
    }
}
