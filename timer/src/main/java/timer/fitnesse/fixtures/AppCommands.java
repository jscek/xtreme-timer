package timer.fitnesse.fixtures;

import timer.base.TimerRecord;
import timer.fitnesse.StaticTimerApp;

import java.io.File;
import java.time.Duration;

public class AppCommands {
    private Long timerId;
    private String filename;

    public void setTimerId(Long timerId) {
        this.timerId = timerId;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setCommand(String command) {
        switch (command.toLowerCase()) {
            case "start":
                StaticTimerApp.app.startTimer(timerId);
                break;
            case "stop":
                StaticTimerApp.app.stopTimer(timerId);
                break;
            case "resume":
                StaticTimerApp.app.startTimer(timerId);
                break;
            case "save":
                StaticTimerApp.app.saveTimerRecords(this.filename);
                break;

        }
    }

    public boolean isRunning() {
        TimerRecord record = StaticTimerApp.app.getTimerById(this.timerId).get();

        return record.isRunning();
    }

    public boolean isSaved() {
        String nameFile = filename + ".txt";
        File file = new File(nameFile);
        return file.exists();
    }
}
