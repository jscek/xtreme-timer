package timer;

import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;
import timer.base.TimerApp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


class TimerReportTest {

    private TimerApp app = new TimerApp();

    public TimerReportTest() throws InterruptedException {
        app.addTimer();
        app.addTimer();
        app.addTimer();
        app.startTimer((long) 1);
        app.startTimer((long) 2);
        app.startTimer((long) 3);
        long start = new Date().getTime();
        while (new Date().getTime() - start < 2000L) {
        }
        app.stopTimer(1);
        app.stopTimer(2);
        app.stopTimer(3);
    }

    @Test
    void checkReportLineCount() throws IOException, URISyntaxException {
        assertThat(app.createReport(null, null, "default"));
        assertEquals( 4,countCsvLines());
        Instant start = Instant.now();
        long s = new Date().getTime();
        while (new Date().getTime() - s < 2000L) {        }
        Instant stop = Instant.now();
        app.addTimer();
        app.startTimer((long) 4);
        assertThat(app.createReport(start, stop, "default"));
        assertEquals(countCsvLines(), 2);

    }

    private int countCsvLines() throws URISyntaxException, IOException {

        Reader reader = Files.newBufferedReader(Paths.get("default.csv"));

        List<String[]> list = new ArrayList<>();
        CSVReader csvReader = new CSVReader(reader);
        String[] line;
        int count = 0;
        while ((line = csvReader.readNext()) != null) {
            count++;
        }
        reader.close();
        csvReader.close();

        return count;
    }


}