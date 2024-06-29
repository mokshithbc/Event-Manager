package controller;

import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
    private static final String FROM = "yvvivek9@gmail.com";
    private static final String FROMNAME = "Event Manager";
    private static final String SMTP_USERNAME = "AKIA4WAYNTOMAWJL756A";
    private static final String SMTP_PASSWORD = "BIk53xgeFOfgngaFVIeGQNUXpUPprWcWM2RUPuriOP6k";
    private static final String HOST = "email-smtp.ap-south-1.amazonaws.com";
    private static final int PORT = 587;

    protected static void sendMail(String email, int otp) {
        String subject = "Account verification code";
        String body = "<h1>Your OTP Code</h1>" +
                      "<p>Your OTP code is: " + otp + "</p>";

        // Set properties and create a new session
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM, FROMNAME));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            msg.setSubject(subject);
            msg.setContent(body, "text/html");

            Transport transport = session.getTransport();
            try {
                transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
                transport.sendMessage(msg, msg.getAllRecipients());
                System.out.println("Email sent!");
            } finally {
                transport.close();
            }
        } catch (Exception e) {
            System.out.println("The email was not sent.");
            e.printStackTrace();
        }
    }

    protected static int getOTP() {
        Random random = new Random();
        return 1000 + random.nextInt(9000);
    }
}
