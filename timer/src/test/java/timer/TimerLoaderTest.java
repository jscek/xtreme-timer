package timer;

import org.junit.jupiter.api.Test;
import timer.base.TimerLoader;
import timer.base.TimerRecord;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;

import static org.assertj.core.api.Assertions.*;

class TimerLoaderTest {
    private final TimerLoader loader = new TimerLoader();

    @Test
    void loadFromFileThatDoesNotExist() {
        assertThat(loader.loadFromFile("not_existing_file")).isEqualTo(Collections.emptyList());
    }

    @Test
    void loadFromFileWithNullPath() {
        assertThat(loader.loadFromFile(null)).isEqualTo(Collections.emptyList());
    }

    @Test
    void loadFromFileWithEmptyPath() {
        assertThat(loader.loadFromFile("")).isEqualTo(Collections.emptyList());
    }

}