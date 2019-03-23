package kz.dorm.utils.email;

import kz.dorm.utils.DataConfig;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Email {

    /**
     * Отправить электронное сообщение.
     */
    public static void sendMessage(String to, EmailMessage message, String name) {
        try {
            sendAsHTML(to, message.getMessage(name));
        } catch (MessagingException | IOException ignored) {

        }
    }

    /**
     * Отправить сообщение как HTML.
     */
    private static void sendAsHTML(String to, String html)
            throws MessagingException, IOException {
        MimeMessage message = createMessage(to, html, "text/html; charset=utf-8");
        Transport.send(message);
    }

    /**
     * Создание сообщения.
     */
    private static MimeMessage createMessage(String to, String html, String type)
            throws MessagingException, IOException {
        MimeMessage message = new MimeMessage(createSession());
        message.setContent(html, type);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

        try (InputStream inputStream = ClassLoader
                .getSystemClassLoader()
                .getResourceAsStream(DataConfig.LINK_CONGIG_PROPERTIES)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            message.setFrom(new InternetAddress(properties.getProperty(DataConfig.PROPERTY_MAIL_LOGIN),
                    properties.getProperty(DataConfig.PROPERTY_MAIL_NAME),
                    MimeUtility.mimeCharset("utf-8")));
            message.setSubject(properties.getProperty(DataConfig.PROPERTY_MAIL_TITLE),
                    MimeUtility.mimeCharset("utf-8"));
        } catch (IOException e) {
            throw e;
        }

        return message;
    }

    /**
     * Создание сессии.
     */
    private static Session createSession() throws IOException {
        try (InputStream inputStream = ClassLoader
                .getSystemClassLoader()
                .getResourceAsStream(DataConfig.LINK_CONGIG_PROPERTIES)) {
            Properties properties = new Properties();
            properties.load(inputStream);

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(properties.getProperty(DataConfig.PROPERTY_MAIL_LOGIN),
                            properties.getProperty(DataConfig.PROPERTY_MAIL_PASSWORD));
                }
            });

            return session;
        } catch (Exception e) {
            throw e;
        }
    }
}
