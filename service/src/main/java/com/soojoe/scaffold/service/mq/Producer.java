package com.soojoe.scaffold.service.mq;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息生产者
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/24 11:16
 */
@Slf4j
@Component
public class Producer implements RabbitTemplate.ConfirmCallback{

  private RabbitTemplate rabbitTemplate;

  @Autowired
  public Producer(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
    this.rabbitTemplate.setConfirmCallback(this);
  }

  public void send(String msg) {
    CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
    log.info("course serice send: " + correlationData.getId());
    this.rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ROUTINGKEY, msg, correlationData);
  }

  @Override
  public void confirm(CorrelationData correlationData, boolean ack, String cause) {
    System.out.println("confirm: " + correlationData.getId());
  }
}
