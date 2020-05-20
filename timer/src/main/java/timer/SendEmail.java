package timer;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail {

	public static void send(String to, String subject, String text, String path) {
		PropertiesReader propertiesReader = new PropertiesReader();

		String host = propertiesReader.getProperties("host", "smtp.wp.pl");
		final String user = propertiesReader.getProperties("user", "extremetimerPE2020@wp.pl");
		final String password = propertiesReader.getProperties("password", "PErules1");

		if (to == null) {
			to = propertiesReader.getProperties("to", "extremetimerPE2020@wp.pl");
		}

		String mailSubject = subject;
		String mailText = text;
		String attachmentSource = path;

		//Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.stmp.port", 465);

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(user, password);
					}
				});

		//Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(mailSubject);

			Multipart multipart = new MimeMultipart();
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText(mailText);

			MimeBodyPart attachmentBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(attachmentSource);
			attachmentBodyPart.setDataHandler(new DataHandler(source));
			attachmentBodyPart.setFileName(path);

			multipart.addBodyPart(textBodyPart);
			multipart.addBodyPart(attachmentBodyPart);
			message.setContent(multipart);

			//send the message
			Transport.send(message);

			String mes = "message sent successfully...";
			System.out.println(mes);


		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
