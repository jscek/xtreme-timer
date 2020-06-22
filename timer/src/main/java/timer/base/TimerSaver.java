package timer.base;

import timer.base.TimerRecord;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class TimerSaver {
    ObjectOutputStream writer;
    public void saveToFile(List<TimerRecord> records, String filename) throws IOException {
        if (records != null && !records.isEmpty()) {
            writer = new ObjectOutputStream(new FileOutputStream(filename + ".txt"));
            writer.writeObject(records);
            //records.forEach(this::writeRecordToFile);
            writer.close();
        }
}

    private void writeRecordToFile(TimerRecord timeRecord) {
        try {
            writer.writeObject(getRecordAsString(timeRecord));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRecordAsString(TimerRecord record) {
        return record.getId() + ";" + record.getProjectName() + ";" + record.getGlobalStartTime() + ";" + record.getStartTime() + ";" +
                record.getStopTime() + ";" + record.isRunning() + ";" + record.getDuration().getSeconds() + ";";
    }
}
