package timer.base;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class TimerRecord implements Serializable {
    private final Long id;
    private final String projectName;
    private Instant startTime;
    private Instant globalStartTime;
    private Instant stopTime;
    private boolean isRunning = false;
    private Duration duration;
    private Duration limit;



    List<TimerSnapshot> snapshots = new ArrayList<>();

    public TimerRecord(Long id) {
        this(id, "--");
    }

    public TimerRecord(Long id, String projectName) {
        this.id = id;
        this.projectName = projectName;
    }

    public TimerRecord(Long id, String projectName, Instant globalStartTime, Instant startTime, Instant stopTime, boolean isRunning, Duration duration) {
        this.id = id;
        this.projectName = projectName;
        this.startTime = startTime;
        this.globalStartTime = globalStartTime;
        this.stopTime = stopTime;
        this.isRunning = isRunning;
        this.duration = duration;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getGlobalStartTime() { return globalStartTime; }

    public Long getId() {
        return id;
    }

    public void startTimer() {
        if (!isRunning) {
            startTime = Instant.now();
            globalStartTime = startTime;
            this.isRunning = true;
        }
    }

    public void stopTimer() {
        if (isRunning) {
            duration = getDuration();
            stopTime = Instant.now();
            snapshots.add(new TimerSnapshot(startTime, stopTime));
            isRunning = false;
        }
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
        Instant stopTime;
        if(this.stopTime==null)
            stopTime=Instant.now();
        else
            stopTime=this.stopTime;

        return this.startTime.compareTo(start) >= 0
                && stopTime.compareTo(stop) <= 0;
    }

    public List<TimerSnapshot> getSnapshots() {
        return snapshots;
    }

    public void setSnapshots(List<TimerSnapshot> snapshots) {
        this.snapshots = snapshots;
    }


    public class TimerSnapshot implements Serializable {
        Instant start;
        Instant stop;
        Duration duration;
        LocalDate date;

        public TimerSnapshot(Instant start, Instant stop, Duration duration, LocalDate date) {
            this.start = start;
            this.stop = stop;
            this.duration = duration;
            this.date = date;
        }

        public TimerSnapshot(Instant start, Instant stop) {
            this.start = start;
            this.stop = stop;
            this.duration = Duration.between(start, stop);
            this.date = start.atZone(ZoneId.systemDefault()).toLocalDate();
        }

        public Instant getStart() {
            return start;
        }

        public void setStart(Instant start) {
            this.start = start;
        }

        public Instant getStop() {
            return stop;
        }

        public void setStop(Instant stop) {
            this.stop = stop;
        }

        public void setDuration(Duration duration) {
            this.duration = duration;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public Duration getDuration() {
            return duration;
        }

        public LocalDate getDate() {
            return date;
        }
    }
}
