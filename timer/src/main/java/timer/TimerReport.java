package timer;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;

public class TimerReport {

    public void saveReport(String filename, ArrayList<ArrayList<String>> content) {
        File file = new File(filename);
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);

            for (ArrayList<String> row : content) {
                writer.writeNext(row.toArray(new String[0]));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ArrayList<String>> createReportContent(Instant start, Instant stop, ArrayList<TimerRecord> list) {

        ArrayList<String> headers = new ArrayList<String>();
        ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>();

        headers.add("projectName");
        headers.add("time");

        rows.add(headers);

        for (TimerRecord record : list) {
            if (this.checkIfBetween(start, stop, record)) {
                ArrayList<String> row = new ArrayList<>();
                row.add(record.getName());

                row.add(displayDuration(record.getDuration().getSeconds()));
                rows.add(row);
            }
        }

        return rows;
    }

    public boolean checkIfBetween(Instant start, Instant stop, TimerRecord record) {

        if (record.getStartTime() == null)
            return false;

        if (record.getStartTime().compareTo(start) > 0
                && (record.getStopTime() == null || record.getStopTime().compareTo(stop) < 0)) {
            return true;
        } else
            return false;

    }

    private String displayDuration(long duration) {
        return String.format("%d:%02d:%02d", duration / 3600, (duration % 3600) / 60, (duration % 60));
    }
}
