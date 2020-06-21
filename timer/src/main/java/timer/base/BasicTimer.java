package timer.base;

interface BasicTimer {
    void start();
    void createTimer(String projectName);
    void startTimer(long id);
    void stopTimer(long id);
    void resumeTimer(long id);
}
