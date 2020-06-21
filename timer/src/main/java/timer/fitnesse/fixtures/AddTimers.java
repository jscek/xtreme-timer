package timer.fitnesse.fixtures;

import timer.base.TimerApp;
import timer.fitnesse.StaticTimerApp;

public class AddTimers {
    private TimerApp timerApp;

    public void setProjectName(String projectName) {
        timerApp = StaticTimerApp.app;

        timerApp.createTimer(projectName);
    }

    public int timersCount() {
        return timerApp.getTimerRecords().size();
    }
}