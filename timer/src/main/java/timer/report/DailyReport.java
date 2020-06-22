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

public class DailyReport {
    public void saveReport(String filename, TimerRecord timer) {
        if (!filename.endsWith(".csv")) {
            filename += "_daily.csv";
        }

        File file = new File(filename);
        try {
            FileWriter outfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outfile);

            Map<LocalDate, Duration> dailyWork = new HashMap<>();
            for (TimerRecord.TimerSnapshot snapshot : timer.getSnapshots()) {
                if (dailyWork.containsKey(snapshot.getDate())) {
                    dailyWork.put(snapshot.getDate(), snapshot.getDuration());
                } else {
                    Duration currentDuration = dailyWork.get(snapshot.getDate());
                    //dailyWork.put(snapshot.getDate(), )
                }
            }

            //ArrayList<ArrayList<String>> content = this.createReportContent(timerRecords);

//            for (ArrayList<String> row : content) {
//                writer.writeNext(row.toArray(new String[0]));
//            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
