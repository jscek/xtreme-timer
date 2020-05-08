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
		isRunning = false;
		stopTime = Instant.now();
		duration = getDuration();
	}

	public Instant getStopTime() {
		return stopTime;

	}

	public Duration getDuration() {
		if (startTime == null) {
			return Duration.ZERO;
		} else if (stopTime == null) {
			return Duration.between(startTime, Instant.now());
		} else if (duration != null) {
			return duration.plus(Duration.between(startTime, stopTime));
		} else {
			return Duration.between(startTime, stopTime);
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
