package com.ecom.musica.buisness.impl;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSender {

    public void sendMail() throws Exception {
        String smtpHost = "smtp.gmail.com";
        String from = "dridi.med.abderezak@gmail.com";
        String to = "m_dridi@esi.dz";
        String username = "dridi.med.abderezak@gmail.com";
        String password = "chichirendan";

        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Hello");
        MimeMultipart mimeMultipart = construireMsg();
        message.setContent(mimeMultipart);
        Transport tr = session.getTransport("smtp");
        tr.connect(smtpHost, username, password);
        message.saveChanges();

        // tr.send(message);
        /**
         * Genere l'erreur. Avec l authentification, oblige d utiliser
         * sendMessage meme pour une seule adresse...
         */

        tr.sendMessage(message, message.getAllRecipients());
        tr.close();

    }

    private MimeMultipart construireMsg() {
        File file = new File("C:/Users/zico/Downloads/procedurestage.docx");
        FileDataSource datasource = new FileDataSource(file);
        DataHandler handler = new DataHandler(datasource);
        MimeBodyPart pj = new MimeBodyPart();
        try {
            pj.setDataHandler(handler);
            pj.setFileName(datasource.getName());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        MimeBodyPart content = new MimeBodyPart();
        try {
            content.setContent("Texte du message", "text/plain");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        MimeMultipart mimeMultipart = new MimeMultipart();
        try {
            mimeMultipart.addBodyPart(content);
            mimeMultipart.addBodyPart(pj);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return mimeMultipart;
    }
}

