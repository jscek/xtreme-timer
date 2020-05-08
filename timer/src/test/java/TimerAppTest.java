import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import timer.TimerApp;

import java.io.File;

public class TimerAppTest {

	@Test
	public void createInstance() {

		TimerApp timerApp = new TimerApp();

		assertThat(timerApp.getTimerRecords()).isEmpty();

	}

	@Test
	public void addTimer() {

		TimerApp timerApp = new TimerApp();
		timerApp.addTimer();
		assertThat(timerApp.getTimerRecords()).isNotEmpty();
		assertThat(timerApp.getTimerRecords().get(0).getId()).isEqualTo(1);
		timerApp.addTimer();
		assertThat(timerApp.getTimerRecords().get(1).getId()).isEqualTo(2);

	}

	@Test
	public void startTimer() {
		TimerApp timerApp = new TimerApp();
		timerApp.addTimer();
		timerApp.startTimer(1L);
		assertThat(timerApp.getTimerRecords().get(0).isRunning()).isTrue();


	}

	@Test
	public void stopTimer() {
		TimerApp timerApp = new TimerApp();
		timerApp.addTimer();
		timerApp.startTimer(1L);
		timerApp.stopTimer(1L);
		assertThat(timerApp.getTimerRecords().get(0).isRunning()).isFalse();

	}

	@Test
	public void resumeTimer() {
		TimerApp timerApp = new TimerApp();
		timerApp.addTimer();
		timerApp.startTimer(1L);
		timerApp.stopTimer(1L);
		timerApp.resumeTimer(1L);
		assertThat(timerApp.getTimerRecords().get(0).isRunning()).isTrue();
	}

	@Test
	public void saveRecords() {
		TimerApp timerApp = new TimerApp();
		timerApp.addTimer();
		timerApp.startTimer(1L);
		timerApp.stopTimer(1L);
		timerApp.saveTimerRecords("tmp");

		File file = new File("tmp.txt");
		assertThat(file)
				.exists()
				.isNotEmpty();

		file.delete();
	}
}
