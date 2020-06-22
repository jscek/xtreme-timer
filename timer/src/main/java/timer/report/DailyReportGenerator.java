package timer.report;

import com.opencsv.CSVWriter;
import timer.base.TimerRecord;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DailyReportGenerator {
    private final List<String> headers = new ArrayList<String>() {{
        add("project_name");
        add("date");
        add("time");
    }};

    public void saveReport(String filename, TimerRecord timer) {
        if (!filename.endsWith(".csv")) {
            filename += "_daily.csv";
        }

        File file = new File(filename);
        try {
            FileWriter outfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outfile);

            Map<String, Duration> dailyWork = new HashMap<>();
            for (TimerRecord.TimerSnapshot snapshot : timer.getSnapshots()) {
                if (!dailyWork.containsKey(snapshot.getDate().toString())) {
                    dailyWork.put(snapshot.getDate().toString(), snapshot.getDuration());
                } else {
                    Duration currentDuration = dailyWork.get(snapshot.getDate().toString());
                    dailyWork.put(snapshot.getDate().toString(), currentDuration.plus(snapshot.getDuration()));
                }
            }

            writer.writeNext(headers.toArray(new String[0]));

            for (Map.Entry<String, Duration> entry : dailyWork.entrySet()) {
                String[] row = new String[3];
                row[0] = timer.getProjectName();
                row[1] = entry.getKey();
                row[2] = displayDuration(entry.getValue().getSeconds());
                writer.writeNext(row);
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getHeader() {
        StringBuilder sb = new StringBuilder();
        for (String header: headers) {
            sb.append(header).append(",");
        }
        return sb.toString();
    }

    private String displayDuration(long duration) {
        return String.format("%d:%02d:%02d", duration / 3600, (duration % 3600) / 60, (duration % 60));
    }
}
