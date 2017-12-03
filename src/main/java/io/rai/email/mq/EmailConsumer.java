package io.rai.email.mq;

import io.rai.email.entity.Email;
import io.rai.email.service.EmailService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailConsumer {

  @Autowired
  private EmailService emailService;

  @JmsListener(destination = "sample.queue")
  public void receiveQueue(Email email) {
    log.info("Enter. email subject: {}.", email.getSubject());
    emailService.send(email);
    log.info("Exit.");
  }

}