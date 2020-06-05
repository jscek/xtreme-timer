package timer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TimerSaver {
    public void saveToFile(List<TimerRecord> records, String filename) throws IOException {
        try {
            if (records != null && !records.isEmpty()) {
                FileWriter writeFile = new FileWriter(filename + ".txt");
                records.forEach(timeRecord -> writeRecordToFile(writeFile, timeRecord));
                writeFile.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
}

    private void writeRecordToFile(FileWriter writeFile, TimerRecord timeRecord) {
        try {
            writeFile.write(getRecordAsString(timeRecord));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRecordAsString(TimerRecord timeRecord) {
        return timeRecord.getId() + ";" + timeRecord.getProjectName() + ";" + timeRecord.getStartTime() + ";" +
                timeRecord.getStopTime() + ";" + timeRecord.getDuration().getSeconds() + ";" + timeRecord.isRunning() + "\n";
    }
}
