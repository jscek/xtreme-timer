import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import timer.TimerRecord;
import timer.TimerSaver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TimerSaverTest {

    @AfterEach
    public void removeFile() {
        new File("tmp.txt").delete();
    }

    @Test
    public void saveTimersToFile() throws IOException {
        TimerRecord record1 = new TimerRecord(1L);
        TimerRecord record2 = new TimerRecord(2L);
        List<TimerRecord> records = new ArrayList<>();
        records.add(record1);
        records.add(record2);

        TimerSaver saver = new TimerSaver();
        saver.saveToFile(records, "tmp");

        Assertions.assertThat(new File("tmp.txt"))
                .exists()
                .isNotEmpty();
    }

    @Test
    public void fileNotCreatedWhen0OrNullRecords() throws IOException {
        TimerSaver saver = new TimerSaver();
        saver.saveToFile(null, "tmp");

        Assertions.assertThat(new File("tmp.txt"))
                .doesNotExist();

        saver.saveToFile(new ArrayList<>(), "tmp");

        Assertions.assertThat(new File("tmp.txt"))
                .doesNotExist();
    }
}
