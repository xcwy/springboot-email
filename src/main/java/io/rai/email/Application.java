package io.rai.email;

import com.google.common.collect.Lists;

import io.rai.email.entity.Email;
import io.rai.email.mq.EmailProducer;
import io.rai.email.service.EmailService;

import lombok.extern.slf4j.Slf4j;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.ErrorHandler;

import java.io.File;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.mail.internet.MimeMessage;

/**
 * Created by rai on 2017/12/3.
 */
@Slf4j
@SpringBootApplication
@EnableJms
public class Application {

  @Autowired
  private EmailService emailService;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public Queue queue() {
    return new ActiveMQQueue("sample.queue");
  }
}
