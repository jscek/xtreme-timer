package timer.base;

import java.time.Instant;

public interface TimerReports {
    String createSummaryReport(Instant start, Instant stop, String filename);
    String createDailyReport(long id, String filename);
}
