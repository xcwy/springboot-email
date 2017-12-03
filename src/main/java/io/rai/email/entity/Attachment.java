package io.rai.email.entity;

import lombok.Data;

import java.io.File;
import java.io.Serializable;

/**
 * Created by rai on 2017/12/3.
 */
@Data
public class Attachment implements Serializable {

  private static final long serialVersionUID = -908164526540584467L;

  private String name;

  private File file;
}
