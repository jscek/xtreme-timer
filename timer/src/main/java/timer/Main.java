package timer;


import timer.base.TimerCommandLineApp;

public class Main {
	public static void main(String[] args) {
		TimerCommandLineApp timerApp = new TimerCommandLineApp();
		timerApp.start();
		Runtime.getRuntime().exit(0);
	}
}
