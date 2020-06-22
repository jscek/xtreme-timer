package timer.report;

import com.opencsv.CSVWriter;
import timer.base.TimerRecord;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CSVReport implements TimerReport {
    private final ArrayList<String> headers = new ArrayList<>(Arrays.asList(
            "project_name", "time", "started", "update"
    ));

    @Override
    public void saveReport(String filename, List<TimerRecord> timerRecords) {
        if (!filename.endsWith(".csv")) {
            filename += ".csv";
        }

        File file = new File(filename);
        try {
            FileWriter outfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outfile);

            ArrayList<ArrayList<String>> content = this.createReportContent(timerRecords);

            for (ArrayList<String> row : content) {
                writer.writeNext(row.toArray(new String[0]));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private ArrayList<ArrayList<String>> createReportContent(List<TimerRecord> timerRecords) {
        ArrayList<ArrayList<String>> rows = new ArrayList<>();
        rows.add(this.headers);

        for (TimerRecord record : timerRecords) {
            ArrayList<String> row = new ArrayList<>();

            row.add(record.getProjectName());
            row.add(displayDuration(record.getDuration().getSeconds()));
            row.add(displayDates(record.getGlobalStartTime()));
            row.add(displayDates(record.getStopTime()));

            rows.add(row);
        }

        return rows;
    }

    private String displayDuration(long duration) {
        return String.format("%d:%02d:%02d", duration / 3600, (duration % 3600) / 60, (duration % 60));
    }

    private String displayDates(Instant date) {
        if (date == null) {
            return "---";
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.format(Date.from(date));
        }
    }
}
