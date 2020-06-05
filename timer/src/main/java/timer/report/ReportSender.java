package timer.report;

import timer.PropertiesReader;

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
        PropertiesReader propertiesReader = new PropertiesReader();

        String host = propertiesReader.getProperties("host", "smtp.wp.pl");
        final String user = propertiesReader.getProperties("user", "extremetimerPE2020@wp.pl");
        final String password = propertiesReader.getProperties("password", "PErules1");

        if (receiver == null) {
            receiver = propertiesReader.getProperties("receiver", "extremetimerPE2020@wp.pl");
        }

        //Get the session object
        Properties props = getProperties(host);

        Session session = getSession(user, password, props);

        //Compose the message
        try {
            MimeMessageBuilder messageBuilder = new MimeMessageBuilder(session, user, receiver);
            messageBuilder
                    .setMailSubject(mailSubject)
                    .setMailText(mailText)
                    .setAttachmentSource(attachmentSource);
            MimeMessage message = messageBuilder.build();

            //send the message
            Transport.send(message);

            String mes = "message sent successfully...";
            System.out.println(mes);


        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static MimeMessage getMimeMessage(String receiver, String mailSubject, String mailText, String attachmentSource, String user, Session session) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(user));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
        message.setSubject(mailSubject);

        Multipart multipart = new MimeMultipart();
        MimeBodyPart textBodyPart = new MimeBodyPart();
        textBodyPart.setText(mailText);

        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(attachmentSource);
        attachmentBodyPart.setDataHandler(new DataHandler(source));
        attachmentBodyPart.setFileName(attachmentSource);

        multipart.addBodyPart(textBodyPart);
        multipart.addBodyPart(attachmentBodyPart);
        message.setContent(multipart);
        return message;
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
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.stmp.port", 465);
        return props;
    }
}
