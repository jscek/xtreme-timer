import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import timer.TimerReader;
import timer.TimerRecord;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class TimerReaderTest {
    private final TimerReader reader = new TimerReader();

    @Test
    void readFromFileThatDoesNotExist() {
        Assertions.assertThat(reader.readFromFile("not_existing_file")).isEqualTo(Collections.emptyList());
    }

    @Test
    void readFromFileWithNullPath() {
        Assertions.assertThat(reader.readFromFile(null)).isEqualTo(Collections.emptyList());
    }

    @Test
    void readFromFileWithEmptyPath() {
        Assertions.assertThat(reader.readFromFile("")).isEqualTo(Collections.emptyList());
    }

    @Test
    public void parseSingleLineToTimerRecord() {
        Long id = 1L;
        String projectName = "test";
        Instant startTime = Instant.parse("2020-04-20T08:00:00.00Z");
        Instant stopTime = Instant.parse("2020-04-20T10:00:00.00Z");
        boolean isRunning = false;
        Duration duration = Duration.ofSeconds(7200);

        TimerRecord originalRecord = new TimerRecord(id, projectName, startTime, stopTime, isRunning, duration);

        String line = id + ";" + projectName + ";" + startTime + ";" + stopTime + ";" + isRunning + ";" + duration.getSeconds();

        TimerRecord parsedRecord = reader.getStringAsRecord(line);

        Assertions.assertThat(originalRecord).isEqualToComparingFieldByField(parsedRecord);
    }
}