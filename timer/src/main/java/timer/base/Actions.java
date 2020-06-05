package timer.base;

import timer.base.TimerApp;
import timer.report.ReportSender;
import timer.utils.PropertiesReader;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class Actions {

    public void perform(TimerApp app, String[] input ) {
        switch (input[0].toLowerCase()) {
            case "create":
                app.addTimer(input);
                break;
            case "start":
                app.startTimer(Long.valueOf(input[1]));
                break;
            case "stop":
                app.stopTimer(Long.parseLong(input[1]));
                break;
            case "resume":
                app.resumeTimer(Long.parseLong(input[1]));
                break;
            case "setlimit":
                app.setLimit(Long.parseLong(input[1]), Duration.ofSeconds(Long.parseLong(input[2])));
                break;
            case "save":
                app.saveTimerRecords(input[1]);
                break;
            case "read":
                app.loadTimerRecords(input[1]);
                break;
            case "quit":
                app.shouldFinish = true;
                saveToStorage(app);
                break;
            case "report":
                createReport(app, input);
                break;
            case "sendemail":
                sendEmail(app, input);
                break;
            case "refresh":
                break;
        }
    }

    private void saveToStorage(TimerApp app) {
        String filename = PropertiesReader.getInstance().getProperty("storage", "storage");
        filename = filename.substring(0, filename.indexOf("."));
        if (shouldSave()) {
            app.saveTimerRecords(filename);
        }
    }

    private boolean shouldSave() {
        return PropertiesReader.getInstance().getProperty("saveOnExit", "false").equals("true");
    }

    private void sendEmail(TimerApp app, String[] input) {
        if (input.length == 1) {
            ReportSender.send("extremetimerPE2020@wp.pl", "Test3", "This is the report", app.createReport(null, null, "report"));
        } else if (input.length == 4) {
            ReportSender.send(input[1], input[2], input[3], app.createReport(null, null, "report"));
        } else {
            System.out.println("Wrong parameters provided");
        }
    }

    private void createReport(TimerApp app, String[] input) {
        if (input.length == 1) {
            app.createReport(null, null, "default");
        } else {
            LocalDate date = LocalDate.parse(input[1]);
            Instant start = date.atStartOfDay(ZoneId.of("Europe/Paris")).toInstant();
            LocalDate date2 = LocalDate.parse(input[2]);
            Instant stop = date2.atStartOfDay(ZoneId.of("Europe/Paris")).toInstant();
            if (input.length == 3)
                app.createReport(start, stop, "default");
            else
                app.createReport(start, stop, input[3]);
        }
    }
}
