package io.rai.email.service;

import io.rai.email.entity.Email;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.File;

import javax.mail.internet.MimeMessage;

/**
 * Created by rai on 2017/12/3.
 */
@Slf4j
@Component
public class EmailService {

  @Autowired
  private JavaMailSender mailSender;

  /**
   * 发送邮件
   */
  public void send(Email email) throws Exception {
    log.info("Enter.");
    if (CollectionUtils.isEmpty(email.getAttachments())) {
      log.info("Simple email");
      sendSimpleMail(email);
    } else {
      log.info("Attachments email");
      sendAttachmentsMail(email);
    }
  }

  /**
   * 发送普通的文字邮件
   */
  private void sendSimpleMail(Email email) throws Exception {

    SimpleMailMessage message = new SimpleMailMessage();

    message.setFrom(email.getSender());
    message.setTo(email.getAddressee());
    message.setSubject(email.getSubject());
    message.setText(email.getText());
    if (!CollectionUtils.isEmpty(email.getCc())) {
      message.setCc(email.getCc().toArray(new String[email.getCc().size()]));
    }

    mailSender.send(message);
  }

  /**
   * 发送带附件的邮件
   */
  private void sendAttachmentsMail(Email email) throws Exception {

    MimeMessage mimeMessage = mailSender.createMimeMessage();

    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
    helper.setFrom(email.getSender());
    helper.setTo(email.getAddressee());
    helper.setSubject(email.getSubject());
    helper.setText(email.getText());
    if (!CollectionUtils.isEmpty(email.getCc())) {
      helper.setCc(email.getCc().toArray(new String[email.getCc().size()]));
    }

    // TODO: 2017/12/3 add attachment 
    FileSystemResource file = new FileSystemResource(new File("logo.png"));
    helper.addAttachment("附件-1.jpg", file);
    helper.addAttachment("附件-2.jpg", file);

    mailSender.send(mimeMessage);
  }


  // TODO: 2017/12/3 tobe finish
  public void sendInlineMail() throws Exception {

    MimeMessage mimeMessage = mailSender.createMimeMessage();

    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
    helper.setFrom("wuyue.xc@gmail.com");
    helper.setTo("503756322@qq.com");
    helper.setSubject("主题：嵌入静态资源");
    helper.setText("<html><body><img src=\"cid:weixin\" ></body></html>", true);

    FileSystemResource file = new FileSystemResource(new File("logo.png"));
    helper.addInline("weixin", file);

    mailSender.send(mimeMessage);
  }
}
