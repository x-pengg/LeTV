package com.maybe.live.service.impl;

import com.maybe.live.kit.Mail;
import com.maybe.live.kit.MailKit;
import com.maybe.live.service.ISendMailService;
import com.maybe.live.support.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.net.URL;
import java.text.MessageFormat;

/**
 * @author: Tate
 * @date: 2016/5/23 15:37
 */
@Service
public class SendMailServiceImpl implements ISendMailService {

    private static final Logger log = LoggerFactory.getLogger(SendMailServiceImpl.class);

    @Override
    public void sendEmailOfRegistered(String mailAddress, String token) {
        String baseUrl = RequestContext.get().getBaseURI();
        String mailTemplate = "";
        URL resource = this.getClass().getClassLoader().getResource("mail/activate_account.txt");
        try {
            mailTemplate = MailKit.getMailTemplate(resource.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String activateUrl = baseUrl + "/active/" + token;
        activateUrl = "<a href=" + activateUrl + ">" + activateUrl + "</a>";
        String mailMessage = MessageFormat.format(mailTemplate, activateUrl);
        send(mailAddress, "[懵逼直播] 账号激活", mailMessage);
        log.info("已发送账号激活邮件至: {}", mailAddress);
    }

    @Override
    public void sendEmailOfForGot(String mailAddress, String token, String ip, String ua, String datetime) {
        String baseUrl = RequestContext.get().getBaseURI();
        String mailTemplate = "";
        URL resource = this.getClass().getClassLoader().getResource("mail/reset_password.txt");
        try {
            mailTemplate = MailKit.getMailTemplate(resource.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String resetUrl = baseUrl + "/reset/" + token;
        resetUrl = "<a href=" + resetUrl + ">" + resetUrl + "</a>";
        String mailMessage = MessageFormat.format(mailTemplate, resetUrl, ip, ua, datetime);
        send(mailAddress, "[懵逼直播] 重设密码", mailMessage);
        log.info("已发送重设密码邮件至: {}", mailAddress);
    }

    public void send(String mailAddress, String mailSubject, String mailMessage) {
        log.info("Sending message:{}", mailMessage);
        Mail mail = MailKit.newDefaultMail(mailAddress);
        mail.setSubject(mailSubject);
        mail.setMessage(mailMessage);
        Runnable task = () -> {
            MailKit.send(mail);
        };
        new Thread(task).start();
    }
}
