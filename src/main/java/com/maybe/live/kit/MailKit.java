package com.maybe.live.kit;

import com.maybe.live.support.Config;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author: Tate
 * @date: 2016/5/20 14:58
 */
public class MailKit {

    public static void send(Mail mail) {
        HtmlEmail email = new HtmlEmail();
        try {
            email.setHostName(mail.getHost());
            email.setCharset(Config.UTF_8);
            email.addTo(mail.getReceiver());
            email.setFrom(mail.getSender(), mail.getName());
            email.setAuthentication(mail.getUsername(), mail.getPassword());
            email.setSubject(mail.getSubject());
            email.setMsg(mail.getMessage());
            email.setSmtpPort(mail.getSmtpPort());
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    public static Mail newDefaultMail(String receiver) {
        Mail mail = new Mail();
        mail.setHost("smtp.qq.com");
        mail.setSmtpPort(587);
        mail.setSender(Config.MAIL_FROM_USER);
        mail.setReceiver(receiver);
        mail.setUsername(Config.MAIL_FROM_USER);
        mail.setPassword(Config.MAIL_FROM_PASSWORD);
        return mail;
    }

    public static String getMailTemplate(URI uri) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(uri)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

}
