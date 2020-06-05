package timer;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

public class TimerReport {
    private final ArrayList<String> headers = new ArrayList<>(Arrays.asList(
        "project_name", "time"
    ));

    public void saveReport(String filename, Instant start, Instant stop, ArrayList<TimerRecord> timerRecords) {
        File file = new File(filename);
        try {
            FileWriter outfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outfile);

            ArrayList<ArrayList<String>> content = this.createReportContent(start, stop, timerRecords);

            for (ArrayList<String> row : content) {
                writer.writeNext(row.toArray(new String[0]));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<ArrayList<String>> createReportContent(Instant start, Instant stop, ArrayList<TimerRecord> timerRecords) {
        ArrayList<ArrayList<String>> rows = new ArrayList<>();
        rows.add(this.headers);

        for (TimerRecord record : timerRecords) {
            if (record.isBetween(start, stop)) {
                ArrayList<String> row = new ArrayList<>();

                row.add(record.getProjectName());
                row.add(displayDuration(record.getDuration().getSeconds()));

                rows.add(row);
            }
        }

        return rows;
    }

    private String displayDuration(long duration) {
        return String.format("%d:%02d:%02d", duration / 3600, (duration % 3600) / 60, (duration % 60));
    }
}
