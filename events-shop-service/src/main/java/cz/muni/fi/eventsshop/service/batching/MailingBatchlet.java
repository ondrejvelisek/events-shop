package cz.muni.fi.eventsshop.service.batching;

import javax.batch.api.Batchlet;
import javax.inject.Named;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Named("mailingBatchlet")
public class MailingBatchlet implements Batchlet {

	@Override
	public String process() throws Exception {
		// Recipient's email ID needs to be mentioned.
		String to = "cyprian.patrik@gmail.com";

		// Sender's email ID needs to be mentioned
		String from = "events-shop@gmail.com";

		// Assuming you are sending email from localhost
		String host = "localhost";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("Upcoming event!");

			// Now set the actual message
			message.setText("We are sending you information about your upcoming event.");

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		}catch (MessagingException mex) {
			mex.printStackTrace();
		}
//		for (int i = 0; i < 10; i++) {
//			System.out.println("Batchlet " + i);
//			Thread.sleep(500);
//		}
		return "PROCESSED";

	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub

	}

}
