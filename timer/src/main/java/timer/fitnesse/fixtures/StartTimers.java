package timer.fitnesse.fixtures;

import timer.base.TimerRecord;
import timer.fitnesse.StaticTimerApp;

public class StartTimers {
    private Long timerId;

    public void setTimerId(Long timerId) {
        this.timerId = timerId;
    }

    public void setStart(boolean start) {
        if (start) {
            StaticTimerApp.app.startTimer(timerId);
        }
    }

    public boolean isRunning() {
        TimerRecord record = StaticTimerApp.app.getTimerById(this.timerId).get();

        return record.isRunning();
    }
}
