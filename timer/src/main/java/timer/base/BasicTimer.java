package timer.base;

import java.time.Duration;

public interface BasicTimer {
    void createTimer(String projectName);
    void startTimer(long id);
    void stopTimer(long id);
    void resumeTimer(long id);
    void setLimit(long id, Duration limit);
}
