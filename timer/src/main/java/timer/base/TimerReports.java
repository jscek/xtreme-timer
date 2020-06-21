package timer.base;

import java.time.Instant;

public interface TimerReports {
    String createReport(Instant start, Instant stop, String filename);

}
