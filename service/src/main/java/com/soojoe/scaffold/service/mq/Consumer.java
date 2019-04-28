package com.soojoe.scaffold.service.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * 消息消费者
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/24 11:17
 */
@Configuration
@RabbitListener(queues = RabbitConfig.QUEUE)
public class Consumer {
  /**
   * 针对消费者配置
   * <p>
   * 1. 设置交换机类型<br/>
   * 2. 将队列绑定到交换机<br/>
   * <p>
   *
   * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
   * HeadersExchange ：通过添加属性key-value匹配
   * DirectExchange:按照routingkey分发到指定队列
   * TopicExchange:多关键字匹配
   */
  @Bean
  public DirectExchange defaultExchange() {
    return new DirectExchange(RabbitConfig.EXCHANGE);
  }

  @Bean
  public Queue queue() {
    return new Queue(RabbitConfig.QUEUE, true);
  }

  @Bean
  public Binding binding() {
    return BindingBuilder.bind(queue()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY);
  }

  @RabbitHandler
  public void process(@Payload String msg) {
    System.out.println("Listener: " + msg);
  }
}
