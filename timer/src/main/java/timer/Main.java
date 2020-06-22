package timer;


import timer.base.TimerApp;
import timer.base.TimerCommandLineApp;

public class Main {
	public static void main(String[] args) {
		TimerApp timerApp = new TimerCommandLineApp();
		timerApp.start();
		Runtime.getRuntime().exit(0);
	}
}
