import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import timer.TimerRecord;

public class TimerRecordTest {


	@Test
	public void startTimer() {

		TimerRecord timerRecord = new TimerRecord(1L);
		timerRecord.startTimer();
		assertThat(timerRecord.getStartTime()).isNotNull();
		assertThat(timerRecord.getId()).isEqualTo(1L);

	}

	@Test
	public void stopTimer() {

		TimerRecord timerRecord = new TimerRecord(1L);
		timerRecord.startTimer();
		assertThat(timerRecord.isRunning()).isTrue();
		timerRecord.stopTimer();
		assertThat(timerRecord.isRunning()).isFalse();
		assertThat(timerRecord.getStopTime()).isNotNull();

	}

	@Test
	public void getDuration() {
		TimerRecord timerRecord = new TimerRecord(1L);
		timerRecord.startTimer();
		assertThat(timerRecord.getDuration()).isNotNull();
	}

	@Test
	public void resumeTimer() throws InterruptedException {
		TimerRecord timerRecord = new TimerRecord(1L);
		timerRecord.startTimer();
		timerRecord.stopTimer();
		Duration duration1 = timerRecord.getDuration();
		timerRecord.resume();
		Thread.sleep(1000);
		timerRecord.stopTimer();
		Duration duration2 = timerRecord.getDuration();

		assertThat(duration2.minus(duration1).toMillis()).isGreaterThan(0);


	}
}
