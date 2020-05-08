package timer;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import timer.SendEmail;

public class SendEmailTest {

    @Test
    public void send() {

        SendEmail newMail = new SendEmail();
        org.junit.jupiter.api.Assertions.assertDoesNotThrow(
                ()-> newMail.main(null)
        );
    }

}
