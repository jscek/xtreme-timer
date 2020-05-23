package timer;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ReportSenderTest {

    private ReportSender newMail = new ReportSender();

    @Test
    public void send() {

        org.junit.jupiter.api.Assertions.assertDoesNotThrow(
                ()-> newMail.send("test@example.com", "test", "test", "test1.jpg")
        );
        assertThat(true);
    }

}