package io.rai.email;

import com.google.common.collect.Lists;

import io.rai.email.entity.Email;
import io.rai.email.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;

/**
 * Created by rai on 2017/12/3.
 */
@SpringBootApplication
public class Application {

  @Autowired
  private EmailService emailService;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @PostConstruct
  public void test() throws Exception {

    Email email = new Email();

    email.setSender("XXXXXX");
    email.setAddressee("XXXXXX");
    email.setSubject("Test email");
    email.setText("This is a test email");
    email.setCc(Lists.newArrayList("XXXXXX"));

    emailService.send(email);
  }
}
