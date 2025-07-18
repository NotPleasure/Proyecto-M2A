/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Email;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String SMTP_USER = "davidnasqui@gmail.com";
    private static final String SMTP_PASS = "lusn qioq wjbu ejcj";

    public static void sendResetEmail(String destinatario, String codigo) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USER, SMTP_PASS);
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(SMTP_USER));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
        msg.setSubject("Tu código de recuperación");

        String html = "<h3>Has solicitado recuperar tu contraseña</h3>"
                + "<p>Este es tu código de verificación:</p>"
                + "<h2 style='color: blue;'>" + codigo + "</h2>"
                + "<p>Si no solicitaste esto, puedes ignorar este mensaje.</p>";
        msg.setContent(html, "text/html; charset=utf-8");

        Transport.send(msg);
    }

    public static void sendCredentialsEmail(String destinatario,
            String usuario,
            String contrasena) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USER, SMTP_PASS);
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(SMTP_USER));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
        msg.setSubject("Tus credenciales de Superusuario");
        String textoPlano
                = "Hola " + usuario + ",\n\n"
                + "Estas son tus credenciales de acceso:\n"
                + "Usuario: " + usuario + "\n"
                + "Contraseña: " + contrasena + "\n\n"
                + "Por favor, cambia tu contraseña en tu primer inicio de sesión y si surge algún error, comunícate con nosotros.";

        msg.setText(textoPlano);
        Transport.send(msg);
    }
}
