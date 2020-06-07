package timer.fitnesse.fixtures;

import timer.base.TimerRecord;
import timer.fitnesse.StaticTimerApp;

public class StopTimers {
    private Long timerId;

    public void setTimerId(Long timerId) {
        this.timerId = timerId;
    }

    public void setStop(boolean stop) {
        if (stop) {
            StaticTimerApp.app.stopTimer(timerId);
        }
    }

    public boolean isRunning() {
        TimerRecord record = StaticTimerApp.app.getTimerById(this.timerId).get();

        return record.isRunning();
    }
}
