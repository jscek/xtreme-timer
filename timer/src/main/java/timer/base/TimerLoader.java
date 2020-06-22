package timer.base;

import timer.base.TimerRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TimerLoader {
    public List<TimerRecord> loadFromFile(String filename)  {
        FileInputStream fis = null;
        List<TimerRecord> list = new ArrayList<>();
        try {
            if (!filename.endsWith(".txt")) {
                filename += ".txt";
            }
            fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (ArrayList<TimerRecord>) ois.readObject();

            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
        return list;
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
        Instant globalStartTime = parseInstant(fields[2]);
        Instant startTime = parseInstant(fields[3]);
        Instant stopTime = parseInstant(fields[4]);
        boolean isRunning = Boolean.parseBoolean(fields[5]);
        Duration duration;
        if (!isRunning) {
            duration = Duration.ofSeconds(Long.parseLong(fields[6]));
        } else {
            duration = Duration.ZERO;
        }
        try {
            List<TimerRecord.TimerSnapshot> asd = (ArrayList<TimerRecord.TimerSnapshot>) new ObjectInputStream(new ByteArrayInputStream(fields[7].getBytes())).readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new TimerRecord(id, projectName, globalStartTime, startTime, stopTime, isRunning, duration);
    }

    private Instant parseInstant(String value) {
        if (value.equals("null")) {
            return null;
        } else {
            return Instant.parse(value);
        }
    }
}
