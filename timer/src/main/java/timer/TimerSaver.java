package timer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TimerSaver {
    public void saveToFile(List<TimerRecord> records, String filename) throws IOException {
        if (records != null && !records.isEmpty()) {
            FileWriter writer = new FileWriter(filename + ".txt");
            records.forEach(record -> writeRecordToFile(writer, record));
            writer.close();
        }
}

    private void writeRecordToFile(FileWriter writer, TimerRecord record) {
        try {
            writer.write(getRecordAsString(record));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRecordAsString(TimerRecord record) {
        return record.getId() + "," + record.getStartTime() + "," +
                record.getStopTime() + "," + record.getDuration().getSeconds() + "," + record.isRunning();
    }
}
