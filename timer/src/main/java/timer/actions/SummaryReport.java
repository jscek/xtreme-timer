package timer.actions;

import timer.base.TimerApp;

import java.time.Instant;
import java.time.LocalDate;
import java.util.TimeZone;

public class SummaryReport extends Actions {

    public SummaryReport() {
        this.action = this.getClass().getSimpleName().toLowerCase();
        this.help = "SummaryReport {from:yyyy-mm-dd} {to:yyyy-mm-dd} {filename}";
    }

    @Override
    protected void perform(String[] input, TimerApp app) {
        createSummaryReport(app, input);
    }

    private void createSummaryReport(TimerApp app, String[] input) {
        if (input.length == 1) {
            app.createSummaryReport(null, null, "default.csv");
        } else {
            LocalDate date = LocalDate.parse(input[1]);
            Instant start = date.atStartOfDay(TimeZone.getDefault().toZoneId()).toInstant();
            LocalDate date2 = LocalDate.parse(input[2]);
            Instant stop = date2.plusDays(1).atStartOfDay(TimeZone.getDefault().toZoneId()).toInstant();
            if (input.length == 3) {
                app.createSummaryReport(start, stop, "default.csv");
            } else {
                app.createSummaryReport(start, stop, input[3]);
            }
        }
    }
}
