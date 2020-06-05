package timer.base;

import timer.base.TimerRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TimerLoader {
    public List<TimerRecord> loadFromFile(String filename) {
        if (filename != null && !filename.isEmpty()) {
            return getTimerRecordsFromFile(filename);
        }
        return new ArrayList<>();
    }

    private List<TimerRecord> getTimerRecordsFromFile(String filename) {
        try (Stream<String> lines = Files.lines(Paths.get(filename))) {
            return lines.map(this::convertStringToTimerRecord).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("File not found");
        }
        return new ArrayList<>();
    }

    public TimerRecord convertStringToTimerRecord(String line) {
        String[] fields = line.split(";");

        Long id = Long.parseLong(fields[0]);
        String projectName = fields[1];
        Instant startTime = parseInstant(fields[2]);
        Instant stopTime = parseInstant(fields[3]);
        boolean isRunning = Boolean.parseBoolean(fields[4]);
        Duration duration;
        if (!isRunning) {
            duration = Duration.ofSeconds(Long.parseLong(fields[5]));
        } else {
            duration = Duration.ZERO;
        }
        return new TimerRecord(id, projectName, startTime, stopTime, isRunning, duration);
    }

    private Instant parseInstant(String value) {
        if (value.equals("null")) {
            return null;
        } else {
            return Instant.parse(value);
        }
    }
}
