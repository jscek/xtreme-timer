package timer.report;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

class MimeMessageBuilder {
    private String receiver;
    private String mailSubject = "No subject";
    private String mailText = "Empty message";
    private String attachmentSource;
    final private Session session;
    private String user;

    MimeMessageBuilder(Session session, String from, String to) {
        this.session = session;
        this.receiver = to;
        this.user = from;
    }

    public MimeMessage build() throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        checkRequiredFields();
        setMailDetails(message);
        setMessageContent(message);
        return message;
    }

    private void setMailDetails(MimeMessage message) throws MessagingException {
        message.setFrom(new InternetAddress(user));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
        message.setSubject(mailSubject);
    }

    private void setMessageContent(MimeMessage message) throws MessagingException {
        MimeBodyPart textBodyPart = new MimeBodyPart();
        textBodyPart.setText(mailText);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(textBodyPart);
        addAttachment(multipart);
        message.setContent(multipart);
    }

    private void addAttachment(Multipart multipart) throws MessagingException {
        if (attachmentSource != null) {
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachmentSource);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(attachmentSource);
            multipart.addBodyPart(attachmentBodyPart);
        }
    }

    private void checkRequiredFields() {
        if (session == null) {
            throw new IllegalArgumentException("Necessary field is null");
        }
    }

    public MimeMessageBuilder setTo(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public MimeMessageBuilder setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
        return this;
    }

    public MimeMessageBuilder setMailText(String mailText) {
        this.mailText = mailText;
        return this;
    }

    public MimeMessageBuilder setAttachmentSource(String attachmentSource) {
        this.attachmentSource = attachmentSource;
        return this;
    }

    public MimeMessageBuilder setFrom(String user) {
        this.user = user;
        return this;
    }
}
