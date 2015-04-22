/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.quadrum.service.util;

/**
 *
 * @author vcisneros
 */
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

//**********************************************
/**
 * Clase que realiza el envio de correo adjuntando o no archivos
 */
public class EnviarCorreo {

    private static final String CORREO = "nomifast@quadrum.mx";
    private static final String HOST = "quadrum.mx";
    private static final String PASS = "Quadrum201";
    private static final String PORT = "587";

    private static final String CORREOaux = "nomiquadrum@gmail.com";
    private static final String HOSTaux = "smtp.gmail.com";
    private static final String PASSWORDaux = "Quadrum*14";
    private static final String PORTaux = "587";

    // ******* Constructor *******
    public EnviarCorreo() {
        super();
    }

    /**
     * Método que realiza el envio de correo electrónico con XML Y PDF
     *
     * @param correo Dirección electronica destinataria
     * @param reciboXml
     * @param reciboPdf
     * @param asunto Asunto del correo
     * @param mensaje Mensaje del correo
     * @throws MessagingException
     */
//    public void enviaEmail(String correo, File reciboXml, File reciboPdf) throws MessagingException {
//
//            try {
//                String from = CORREO;
//                String to = correo;
//                String subject = ASUNTO_MAIL_TIMBRADO;
//                String message = MENSAJE_MAIL_TIMBRADO;
//                String login = CORREO;
//                String password = PASS;
//                String destinos[] = to.split(";");
//                Properties props = new Properties();
//                props.setProperty("mail.host", HOST);
//                props.setProperty("mail.smtp.port", PORT);
//                props.setProperty("mail.smtp.auth", "true");
//                props.setProperty("mail.smtp.starttls.enable", "false");
//                props.put("mail.smtp.socketFactory.port", PORT);
//                props.put("mail.smtp.socketFactory.fallback", "false");
//
//                Authenticator auth = new SMTPAuthenticator(login, password);
//
//                Session session = Session.getInstance(props, auth);
//
//                MimeMessage msg = new MimeMessage(session);
//
//                BodyPart texto = new MimeBodyPart();
//                texto.setText(message);
//
//                msg.setText(message);
//                msg.setSubject(subject);
//                msg.setFrom(new InternetAddress(from));
//                InternetAddress receptores = new InternetAddress(to);
//                
//                msg.addRecipient(Message.RecipientType.TO, receptores);
//                Multipart mp = new MimeMultipart();
//                mp.addBodyPart(texto);
//                    MimeBodyPart attachment = new MimeBodyPart();
//                try {
//                    //se agrega XML
//                    attachment.attachFile(reciboXml);
//                    mp.addBodyPart(attachment);
//                    //se agrega PDF
//                    MimeBodyPart attachmentXML = new MimeBodyPart();
//                    attachmentXML.attachFile(reciboPdf);
//                    mp.addBodyPart(attachmentXML);
//                } catch (IOException ex) {
//                    Logger.getLogger(EnviarCorreo.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//                //*******************************************************
//                msg.setContent(mp);
//
//                Transport.send(msg);
//            } catch (MessagingException m) {
//                String from = CORREOaux;
//                String to = correo;
//                String subject = ASUNTO_MAIL_TIMBRADO;
//                String message = MENSAJE_MAIL_TIMBRADO;
//                String login = CORREOaux;
//                String password = PASSWORDaux;
//                String destinos[] = to.split(";");
//                Properties props = new Properties();
//                props.setProperty("mail.host", HOST);
//                props.setProperty("mail.smtp.port", PORT);
//                props.setProperty("mail.smtp.auth", "true");
//                props.setProperty("mail.smtp.starttls.enable", "false");
//                props.put("mail.smtp.socketFactory.port", PORT);
//                props.put("mail.smtp.socketFactory.fallback", "false");
//
//                Authenticator auth = new SMTPAuthenticator(login, password);
//
//                Session session = Session.getInstance(props, auth);
//
//                MimeMessage msg = new MimeMessage(session);
//
//                BodyPart texto = new MimeBodyPart();
//                texto.setText(message);
//
//                msg.setText(message);
//                msg.setSubject(subject);
//                msg.setFrom(new InternetAddress(from));
//                InternetAddress[] receptores = new InternetAddress[destinos.length];
//                
//                msg.addRecipients(Message.RecipientType.TO, receptores);
//
//                //      msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//                //  msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//                //****** adjuntar PDF ***********************************
//                Multipart mp = new MimeMultipart();
//                mp.addBodyPart(texto);
//                    MimeBodyPart attachment = new MimeBodyPart();
//                try {
//                    attachment.attachFile(reciboXml);
//                    mp.addBodyPart(attachment);
//                    //se agrega XML
//                    MimeBodyPart attachmentXML = new MimeBodyPart();
//                    attachmentXML.attachFile(reciboPdf);
//                    mp.addBodyPart(attachmentXML);
//                } catch (IOException ex) {
//                    Logger.getLogger(EnviarCorreo.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                    
//                //*******************************************************
//                msg.setContent(mp);
//
//                Transport.send(msg);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//
//        }
//  
    /**
         * Método que realiza el envio de correo electrónico con la contraseña
         *
         * @param correo Dirección electronica destinataria
         * @param asunto Asunto del correo
         * @param mensaje Mensaje del correo
         * @throws MessagingException
         */
     public void enviaCredenciales(String correo, String asunto, String mensaje) throws MessagingException {

        try {
            String from = CORREO;
            String to = correo;
            String subject = asunto;
            String message = mensaje;
            String login = CORREO;
            String password = PASS;

            Properties props = new Properties();
            props.setProperty("mail.host", HOST);
            props.setProperty("mail.smtp.port", PORT);
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.smtp.starttls.enable", "false");
            props.put("mail.smtp.socketFactory.port", PORT);
            props.put("mail.smtp.socketFactory.fallback", "false");

            Authenticator auth = new SMTPAuthenticator(login, password);
            Session session = Session.getInstance(props, auth);

            MimeMessage msg = new MimeMessage(session);

            /* System.out.println("Message: "+message);
             System.out.println("Subject: "+subject);
             System.out.println("From: "+from);
             System.out.println("To: "+to);
             System.out.println("Msg: "+msg); */
            msg.setText(message);
            msg.setSubject(subject);
            msg.setFrom(new InternetAddress(from));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            Transport.send(msg);
        } catch (MessagingException m) {

            String from = CORREOaux;
            String to = correo;
            String subject = asunto;
            String message = mensaje;
            String login = CORREOaux;
            String password = PASSWORDaux;

            Properties props = new Properties();
            props.setProperty("mail.host", HOST);
            props.setProperty("mail.smtp.port", PORT);
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.smtp.starttls.enable", "false");
            props.put("mail.smtp.socketFactory.port", PORT);
            props.put("mail.smtp.socketFactory.fallback", "false");

            Authenticator auth = new SMTPAuthenticator(login, password);
            Session session = Session.getInstance(props, auth);

            MimeMessage msg = new MimeMessage(session);

            /* System.out.println("Message: "+message);
             System.out.println("Subject: "+subject);
             System.out.println("From: "+from);
             System.out.println("To: "+to);
             System.out.println("Msg: "+msg); */
            msg.setText(message);
            msg.setSubject(subject);
            msg.setFrom(new InternetAddress(from));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            Transport.send(msg);
        } catch (Exception ex) {
            System.out.println("error " + ex);
        }

    }

   
    private class SMTPAuthenticator extends Authenticator {

        private PasswordAuthentication authentication;

        public SMTPAuthenticator(String login, String password) {
            authentication = new PasswordAuthentication(login, password);
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return authentication;
        }
    }
}
