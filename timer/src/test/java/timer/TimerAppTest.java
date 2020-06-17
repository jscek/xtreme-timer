package timer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import timer.base.Actions;
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

	@Test
	public void addTimerWithLongName() {
		TimerApp timerApp = new TimerApp();
		Actions actions = new Actions();
		String command = "Create Extreme programming project";
		actions.perform(timerApp, command.split(" "));
		assertThat(timerApp.getTimerById(1L).get().getProjectName()).isEqualTo("Extreme programming project");

	}

	@Test
	public void setLimit1() {
		TimerApp timerApp = new TimerApp();
		Actions actions = new Actions();
		String command = "setLimit 1 12h23m13s";
		actions.perform(timerApp, "Create test".split(" "));
		actions.perform(timerApp, command.split(" "));
		Duration duration = Duration.ofSeconds(13);
		duration = duration.plusMinutes(23);
		duration = duration.plusHours(12);
		assertThat(timerApp.getTimerById(1L).get().getLimit()).isEqualTo(duration);

	}

	@Test
	public void setLimit2() {
		TimerApp timerApp = new TimerApp();
		Actions actions = new Actions();
		String command = "setLimit 1 12:23:13";
		actions.perform(timerApp, "Create test".split(" "));
		actions.perform(timerApp, command.split(" "));
		Duration duration = Duration.ofSeconds(13);
		duration = duration.plusMinutes(23);
		duration = duration.plusHours(12);
		assertThat(timerApp.getTimerById(1L).get().getLimit()).isEqualTo(duration);

	}

	@Test
	public void setLimit3() {
		TimerApp timerApp = new TimerApp();
		Actions actions = new Actions();
		String command = "setLimit 1 24h";
		actions.perform(timerApp, "Create test".split(" "));
		actions.perform(timerApp, command.split(" "));
		Duration duration = Duration.ofHours(24);
		assertThat(timerApp.getTimerById(1L).get().getLimit()).isEqualTo(duration);

	}
}
