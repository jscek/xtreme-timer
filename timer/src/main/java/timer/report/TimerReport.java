package timer.report;

import timer.base.TimerRecord;

import java.util.List;

public interface TimerReport {
    void saveReport(String filename, List<TimerRecord> timerRecords);
}
