package timer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TimerLoader {
    public List<TimerRecord> loadFromFile(String filename) {
        if (filename != null && !filename.isEmpty()) {
            try (Stream<String> lines = Files.lines(Paths.get(filename))) {
                return lines.map(this::getStringAsRecord).collect(Collectors.toList());
            } catch (IOException e) {
                System.err.println("File not found");
            }
        }

        return Collections.emptyList();
    }

    public TimerRecord getStringAsRecord(String line) {
        String[] fields = line.split(";");

        Long id = Long.parseLong(fields[0]);
        String projectName = fields[1];
        Instant startTime = Instant.parse(fields[2]);
        Instant stopTime;
        try {
            stopTime = Instant.parse(fields[3]);
        } catch (Exception e) {
            stopTime = null;
//            e.printStackTrace();
        }
        boolean isRunning = Boolean.parseBoolean(fields[4]);
        Duration duration;
        try {
            duration = Duration.ofSeconds(Long.parseLong(fields[5]));
        } catch (Exception e) {
            duration = null;
//            e.printStackTrace();
        }

        return new TimerRecord(id, projectName, startTime, stopTime, isRunning, duration);
    }
}
