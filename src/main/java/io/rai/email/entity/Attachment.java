package io.rai.email.entity;

import lombok.Data;

import java.io.File;

/**
 * Created by rai on 2017/12/3.
 */
@Data
public class Attachment {

  private String name;

  private File file;
}
