package timer;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;

import static org.assertj.core.api.Assertions.*;

class TimerLoaderTest {
    private final TimerLoader loader = new TimerLoader();

    @Test
    void loadFromFileThatDoesNotExist() {
        assertThat(loader.loadFromFile("not_existing_file")).isEqualTo(Collections.emptyList());
    }

    @Test
    void loadFromFileWithNullPath() {
        assertThat(loader.loadFromFile(null)).isEqualTo(Collections.emptyList());
    }

    @Test
    void loadFromFileWithEmptyPath() {
        assertThat(loader.loadFromFile("")).isEqualTo(Collections.emptyList());
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

        TimerRecord parsedRecord = loader.convertStringToTimerRecord(line);

        assertThat(originalRecord).isEqualToComparingFieldByField(parsedRecord);
    }
}