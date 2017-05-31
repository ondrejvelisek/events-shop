package cz.muni.fi.eventsshop.rest.batching;

import com.sun.mail.smtp.SMTPMessage;

import javax.batch.api.Batchlet;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import java.util.Properties;

/**
 * Created by pato on 31.5.2017.
 */
public class SimpleBatchlet2 implements Batchlet {
    @Override
    public String process() throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "805");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("pokemongo.dubnica@gmail.com","pokemongo");
            }
        });

        try {

            SMTPMessage message = new SMTPMessage(session);
            message.setFrom(new InternetAddress("events-shop@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse( "cyprian.patrik@gmail.com" ));

            message.setSubject("You have upcoming event");
            message.setText("Hi, \n " +
                    "we are sending you informations about your upcoming event.");
            //message.setContent("This Is my First Mail Through Java");
            message.setNotifyOptions(SMTPMessage.NOTIFY_SUCCESS);
            int returnOption = message.getReturnOption();
            System.out.println(returnOption);
            Transport.send(message);
            System.out.println("sent");

        }
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return "PROCESSED";
    }

    @Override
    public void stop() throws Exception {

    }
}
