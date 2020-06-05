package timer.report;


import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MimeMessageBuilderTest {

    @Test
    public void createMimeMessage() throws MessagingException {
        Session session = Session.getInstance(new Properties());
        MimeMessageBuilder messageBuilder = new MimeMessageBuilder(session, "me@agh.pl", "you@agh.pl");
        messageBuilder
                .setMailSubject("Subject")
                .setMailText("Test text")
                .setAttachmentSource("aaa");

        MimeMessage message = messageBuilder.build();

        assertThat(message).isNotNull();
        assertThat(message.getSubject()).isEqualTo("Subject");
    }

    @Test
    public void throwExceptionOnNullMandatoryFiled() throws MessagingException {
        MimeMessageBuilder messageBuilder = new MimeMessageBuilder(null, "me@agh.pl", "you@agh.pl");
        messageBuilder
                .setMailSubject("Subject")
                .setMailText("Test text")
                .setAttachmentSource("aaa");

        assertThatThrownBy(messageBuilder::build).isInstanceOf(IllegalArgumentException.class);
    }
}