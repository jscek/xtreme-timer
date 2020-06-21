package timer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import timer.base.TimerApp;

import java.io.File;
import java.time.Duration;

public class TimerAppTest {

	@Test
	public void createInstance() {
		TimerApp timerApp = new TimerApp();
		assertThat(timerApp.getTimerRecords()).isEmpty();
	}

	@Test
	public void addTimer() {
		TimerApp timerApp = new TimerApp();
		timerApp.createTimer("timer1");
		assertThat(timerApp.getTimerRecords()).isNotEmpty();
		assertThat(timerApp.getTimerRecords().get(0).getId()).isEqualTo(1);
		timerApp.createTimer("timer2");
		assertThat(timerApp.getTimerRecords().get(1).getId()).isEqualTo(2);
		timerApp.createTimer("timer2");
		assertThat(timerApp.getTimerRecords().get(1).getId()).isEqualTo(2);
	}

	@Test
	public void addTimerWithDefaultName() {
		TimerApp timerApp = new TimerApp();
		timerApp.createTimer(null);
		assertThat(timerApp.getTimerRecords()).isNotEmpty();
		assertThat(timerApp.getTimerRecords().get(0).getId()).isEqualTo(1);
		timerApp.createTimer("timer2");
		assertThat(timerApp.getTimerRecords().get(1).getId()).isEqualTo(2);
	}

	@Test
	public void startTimer() {
		TimerApp timerApp = new TimerApp();
		timerApp.createTimer("timer");
		timerApp.startTimer(1L);
		assertThat(timerApp.getTimerRecords().get(0).isRunning()).isTrue();
	}

	@Test
	public void stopTimer() {
		TimerApp timerApp = new TimerApp();
		timerApp.createTimer("");
		timerApp.startTimer(1L);
		timerApp.stopTimer(1L);
		assertThat(timerApp.getTimerRecords().get(0).isRunning()).isFalse();
	}

	@Test
	public void resumeTimer() throws InterruptedException {
		TimerApp timerApp = new TimerApp();
		timerApp.createTimer("");
		timerApp.startTimer(1L);
		Thread.sleep(100);
		timerApp.stopTimer(1L);
		Duration durationOnStop = timerApp.getTimerRecords().get(0).getDuration();
		timerApp.resumeTimer(1L);
		Thread.sleep(100);
		assertThat(timerApp.getTimerRecords().get(0).isRunning()).isTrue();
		Duration durationAfterResume = timerApp.getTimerRecords().get(0).getDuration();
		assertThat(durationAfterResume.compareTo(durationOnStop)).isGreaterThan(0);
	}

	@Test
	public void saveRecords() {
		TimerApp timerApp = new TimerApp();
		timerApp.createTimer("");
		timerApp.startTimer(1L);
		timerApp.stopTimer(1L);
		timerApp.saveTimerRecords("tmp");

		File file = new File("tmp.txt");
		assertThat(file)
				.exists()
				.isNotEmpty();

		file.delete();
	}

	@Test
	public void addTimerWithLongName() {
		TimerApp timerApp = new TimerApp();
		String command = "Create Extreme programming project";
		timerApp.actionChain.exec(timerApp, command.split(" "));
		assertThat(timerApp.getTimerById(1L).get().getProjectName()).isEqualTo("Extreme programming project");

	}

	@Test
	public void setLimit1() {
		TimerApp timerApp = new TimerApp();
		String command = "setLimit 1 12h23m13s";
		timerApp.actionChain.exec(timerApp, "Create test".split(" "));
		timerApp.actionChain.exec(timerApp, command.split(" "));
		Duration duration = Duration.ofSeconds(13);
		duration = duration.plusMinutes(23);
		duration = duration.plusHours(12);
		assertThat(timerApp.getTimerById(1L).get().getLimit()).isEqualTo(duration);

	}

	@Test
	public void setLimit2() {
		TimerApp timerApp = new TimerApp();
		String command = "setLimit 1 12:23:13";
		timerApp.actionChain.exec(timerApp, "Create test".split(" "));
		timerApp.actionChain.exec(timerApp, command.split(" "));
		Duration duration = Duration.ofSeconds(13);
		duration = duration.plusMinutes(23);
		duration = duration.plusHours(12);
		assertThat(timerApp.getTimerById(1L).get().getLimit()).isEqualTo(duration);

	}

	@Test
	public void setLimit3() {
		TimerApp timerApp = new TimerApp();
		String command = "setLimit 1 24h";
		timerApp.actionChain.exec(timerApp, "Create test".split(" "));
		timerApp.actionChain.exec(timerApp, command.split(" "));
		Duration duration = Duration.ofHours(24);
		assertThat(timerApp.getTimerById(1L).get().getLimit()).isEqualTo(duration);

	}
}
