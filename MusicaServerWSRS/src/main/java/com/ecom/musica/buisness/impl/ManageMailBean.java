package com.ecom.musica.buisness.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ecom.musica.entities.Commande;

@Stateless
public class ManageMailBean {

    @PersistenceContext(unitName = "EntityManagerPU")
    private EntityManager entityManager;

    public void sendMail(String encodedFile,int commandeId) throws Exception {
        Commande commande = entityManager.find(Commande.class, commandeId);
        String smtpHost = "smtp.gmail.com";
        String from = "dridi.med.abderezak@gmail.com";
        String to = commande.getUtilisateur().getEmail();
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
        MimeMultipart mimeMultipart = construireMsg(encodedFile,commande.getUtilisateur().getUtilisateurId());
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

    private MimeMultipart construireMsg(String encodedFile,int utilisateurId) {
        byte[] bytes = Base64.getDecoder().decode(encodedFile);

        File file = new File("facture"+utilisateurId);
        try {
            FileOutputStream fop = new FileOutputStream(file);
            fop.write(bytes);
            fop.flush();
            fop.close();
        } catch (Exception ex) {
        }
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
        file.delete();
        return mimeMultipart;
    }
}
