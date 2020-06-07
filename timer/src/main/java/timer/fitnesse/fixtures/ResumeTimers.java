package timer.fitnesse.fixtures;

import timer.base.TimerRecord;
import timer.fitnesse.StaticTimerApp;

public class ResumeTimers {
    private Long timerId;

    public void setTimerId(Long timerId) {
        this.timerId = timerId;
    }

    public void setResume(boolean resume) {
        if (resume) {
            StaticTimerApp.app.startTimer(timerId);
        }
    }

    public boolean isRunning() {
        TimerRecord record = StaticTimerApp.app.getTimerById(this.timerId).get();

        return record.isRunning();
    }
}
