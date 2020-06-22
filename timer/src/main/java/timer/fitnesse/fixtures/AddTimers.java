package timer.fitnesse.fixtures;

import timer.base.TimerCommandLineApp;
import timer.fitnesse.StaticTimerApp;

public class AddTimers {
    private TimerCommandLineApp timerApp;

    public void setProjectName(String projectName) {
        timerApp = StaticTimerApp.app;

        timerApp.createTimer(projectName);
    }

    public int timersCount() {
        return timerApp.getTimerRecords().size();
    }
}