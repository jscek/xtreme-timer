package timer;

import java.time.Duration;
import java.time.Instant;

public class TimerRecord {
    private Long id;
    private String projectName;
    private Instant startTime;
    private Instant stopTime;
    private boolean isRunning = false;
    private Duration duration;

    public TimerRecord(Long id) {
        this(id, "--");
    }

    public TimerRecord(Long id, String projectName) {
        this.id = id;
        this.projectName = projectName;
    }

    public TimerRecord(Long id, String projectName, Instant startTime, Instant stopTime, boolean isRunning, Duration duration) {
        this.id = id;
        this.projectName = projectName;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.isRunning = isRunning;
        this.duration = duration;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Long getId() {
        return id;
    }

    public void stopTimer() {
        duration = getDuration();
        stopTime = Instant.now();
        isRunning = false;
    }

    public Instant getStopTime() {
        return stopTime;
    }

    public Duration getDuration() {
        if (isRunning) {
            return getDurationWhenRunning();
        } else {
            return getDurationWhenStopped();
        }

    }

    private Duration getDurationWhenStopped() {
        if (startTime == null && duration == null) {
            return Duration.ZERO;
        } else {
            return duration;
        }
    }

    private Duration getDurationWhenRunning() {
        Duration currentDuration = duration != null ? duration : Duration.ZERO;

        if (stopTime != null) {
            return currentDuration.plus(Duration.between(startTime, stopTime));
        } else {
            return currentDuration.plus(Duration.between(startTime, Instant.now()));
        }

    }

    public boolean isRunning() {
        return isRunning;
    }

    public void resume() {
        stopTime = null;
        startTime = Instant.now();
        isRunning = true;
    }

    public void startTimer() {
        startTime = Instant.now();
        this.isRunning = true;
    }

    public String getProjectName() {
        return projectName;
    }
}
