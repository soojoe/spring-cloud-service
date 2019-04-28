package com.soojoe.scaffold.service.mq;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * rabbit相关配置
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/24 11:07
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitConfig {
  public static final String EXCHANGE   = "scaffold.topic";
  public static final String ROUTINGKEY = "routingkey";
  public static final String QUEUE      = "scaffold.queue";

  private String host;

  private Integer port;

  private String username;

  private String password;

  private Boolean publisherConfirms;

  private String virtualHost;
}
