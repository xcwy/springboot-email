package io.rai.email.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Spring's mail class is enough, don't need this.
 */
@Data
public class Email implements Serializable{

  private static final long serialVersionUID = 7545353581143076231L;

  private String sender;

  private String subject;

  private String addressee;

  private String text;

  private List<String> cc;

  private List<Attachment> attachments;
}
