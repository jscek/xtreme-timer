package timer.base;

import timer.base.TimerRecord;

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

    private void writeRecordToFile(FileWriter writer, TimerRecord timeRecord) {
        try {
            writer.write(getRecordAsString(timeRecord));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRecordAsString(TimerRecord record) {
        return record.getId() + ";" + record.getProjectName() + ";" + record.getStartTime() + ";" +
                record.getStopTime() + ";" + record.isRunning() + ";" + record.getDuration().getSeconds() + "\n";
    }
}
