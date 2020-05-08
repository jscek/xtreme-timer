package timer;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SendEmailTest {

    private SendEmail newMail = new SendEmail();

    @Test
    public void send() {

        org.junit.jupiter.api.Assertions.assertDoesNotThrow(
                ()-> newMail.send("test@example.com", "test", "test", "C:\\Users\\domin\\OneDrive\\Pulpit\\test1.jpg")
        );
        assertThat(true);
    }

}