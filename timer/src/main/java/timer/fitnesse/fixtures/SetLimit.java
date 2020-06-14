package timer.fitnesse.fixtures;

import timer.base.TimerRecord;
import timer.fitnesse.StaticTimerApp;
import java.time.Duration;

public class SetLimit {
    private Long timerId;
    private long seconds;

    public void setTimerId(Long timerId) {
        this.timerId = timerId;
    }

    public void setLimit(Long seconds) {
        Duration duration = Duration.ofSeconds(seconds);
        StaticTimerApp.app.setLimit(timerId, duration);
    }

    public Long whatLimit() {
        TimerRecord record = StaticTimerApp.app.getTimerById(this.timerId).get();
        return record.getLimit().getSeconds();
    }

    public boolean hasLimit() {
        TimerRecord record = StaticTimerApp.app.getTimerById(this.timerId).get();
        if (record.getLimit() == Duration.ZERO){
            return false;
        } else {
            return true;
        }
    }
}