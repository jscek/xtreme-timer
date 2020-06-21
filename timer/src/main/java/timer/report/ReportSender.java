package timer.report;

import timer.utils.PropertiesReader;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class ReportSender {

    public static void send(String receiver, String mailSubject, String mailText, String attachmentSource) {
        PropertiesReader propertiesReader = PropertiesReader.getInstance();

        String host = propertiesReader.getProperty("host", "smtp.wp.pl");
        final String user = propertiesReader.getProperty("user", "extremetimerPE2020@wp.pl");
        final String password = propertiesReader.getProperty("password", "PErules123");

        if (receiver == null) {
            receiver = propertiesReader.getProperty("receiver", "extremetimerPE2020@wp.pl");
        }

        //Get the session object
        Properties props = getProperties(host);

        Session session = getSession(user, password, props);

        //Compose the message
        try {
            MimeMessageBuilder messageBuilder = new MimeMessageBuilder(session, user, receiver);
            messageBuilder.setMailSubject(mailSubject);
            messageBuilder.setMailText(mailText);
            messageBuilder.setAttachmentSource(attachmentSource);
            MimeMessage message = messageBuilder.build();

            //send the message
            Transport.send(message);

            String mes = "message sent successfully...";
            System.out.println(mes);


        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static Session getSession(String user, String password, Properties props) {
        return Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });
    }

    private static Properties getProperties(String host) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.port", 465);
        return props;
    }
}
