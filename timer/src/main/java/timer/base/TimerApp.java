package timer.base;

public abstract class TimerApp implements BasicTimer, TimerPersistence, TimerReports {
    public abstract void start();
    public abstract void finish();
}
