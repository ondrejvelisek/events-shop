package cz.muni.fi.eventsshop.rest.batching;

import com.sun.mail.smtp.SMTPMessage;
import cz.muni.fi.eventsshop.facade.EventFacade;
import cz.muni.fi.eventsshop.facade.WeatherFacade;
import cz.muni.fi.eventsshop.model.Event;
import cz.muni.fi.eventsshop.service.impl.Weather;


import javax.batch.api.Batchlet;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import java.util.List;
import java.util.Properties;

/**
 * Created by pato on 31.5.2017.
 */
@Named("simple")
@ApplicationScoped
public class SimpleBatchlet2 implements Batchlet {

    @Inject
    EventFacade facade;

    @Inject
    private WeatherFacade weatherFacade;

    @Override
    public String process() throws Exception {

        List<Event> events = facade.getUpcomingEvents();

        for(int i = 0; i < events.size(); i++) {

            Weather w = weatherFacade.getWeatherForecast(events.get(i).getCity());

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
                    return new PasswordAuthentication("pokemongo.dubnica@gmail.com", "pokemongo");
                }
            });

            try {

                SMTPMessage message = new SMTPMessage(session);
                message.setFrom(new InternetAddress("events-shop@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(events.get(i).getClient().getEmail()));

                message.setSubject("You have upcoming event");
                message.setText("Hi, "+ events.get(i).getClient().getName() +"\n" +
                        "we are sending you informations about your upcoming event - "+ events.get(i).getName() +".\n" +
                        "Date: " + events.get(i).getDate() + "\n" +
                        "Place: " + events.get(i).getAddress() + ", " + events.get(i).getCity() + "\n" +
                        "Weather: \n" +
                        "  Temperature: " + w.getTemperature() + " Celsius\n" +
                        "  Clouds: " + w.getCloudDescription() + " \n" +
                        "  Wind speed: " + w.getWindSpeed() + " m/s\n" +
                        "  Pressure: " + w.getPressure() + " hPa\n\n" +
                        " Have a nice day, your events-shop team. ");
                //message.setContent("This Is my First Mail Through Java");
                message.setNotifyOptions(SMTPMessage.NOTIFY_SUCCESS);
                int returnOption = message.getReturnOption();
                System.out.println(returnOption);
                Transport.send(message);
                System.out.println("sent");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
        return "PROCESSED";
    }

    @Override
    public void stop() throws Exception {

    }
}
