package timer.base;

public interface TimerPersistence {
    void saveTimerRecords(String filename);
    void loadTimerRecords(String filename);
}
