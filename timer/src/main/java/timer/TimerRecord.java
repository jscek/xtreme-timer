package timer;

import java.time.Duration;
import java.time.Instant;

public class TimerRecord {
    private final Long id;
    private final String projectName;
    private Instant startTime;
    private Instant stopTime;
    private boolean isRunning = false;
    private Duration duration;
    private Duration limit;

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

    public Duration getLimit() {
        if (limit == null) {
            return Duration.ZERO;
        }
        return limit;
    }

    public void setLimit(Duration limit) {
        this.limit = limit;
    }

    public boolean isBetween(Instant start, Instant stop) {
        if (this.startTime == null)
            return false;

        return this.startTime.compareTo(start) > 0
                && (this.stopTime == null || this.stopTime.compareTo(stop) < 0);
    }
}
