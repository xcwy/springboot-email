package io.rai.email.mq;

import com.google.common.collect.Lists;

import io.rai.email.entity.Email;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * Created by rai on 2017/12/3.
 */
@Slf4j
@Component
public class EmailProducer implements CommandLineRunner{

  @Autowired
  private JmsMessagingTemplate jmsMessagingTemplate;

  @Autowired
  private Queue queue;

  public void send(Email email) {
    log.info("Enter. email subject: {}.", email.getSubject());
    this.jmsMessagingTemplate.convertAndSend(this.queue, email);
    log.info("Exit.");
  }

  @Override
  public void run(String... args) throws Exception {
    int flag = 10;
    for (int i = 0; i < flag; i++) {
      Email email = new Email();

      email.setSender("your gmail acount");
      email.setAddressee("addressee");
      email.setCc(Lists.newArrayList("cc"));
      email.setSubject("Test Subject " + i);
      email.setText("This is a test email: " + i);

      send(email);
      log.info("Message: {} was sent to the Queue", i);
    }
    log.info("out of producer");
  }
}