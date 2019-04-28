package com.soojoe.scaffold.service.mq;

import javax.annotation.Resource;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * RabbitMq配置及连接信息
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/23 16:36
 */
@Configuration
public class AmqpConfig {

  @Resource
  private RabbitConfig rabbitConfig;

  /**
   * 创建连接
   *
   * @return
   */
  @Bean
  public CachingConnectionFactory connectionFactory() {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
    connectionFactory.setAddresses(rabbitConfig.getHost());
    connectionFactory.setUsername(rabbitConfig.getUsername());
    connectionFactory.setPassword(rabbitConfig.getPassword());
    connectionFactory.setVirtualHost(rabbitConfig.getVirtualHost());
    // 这里需要显示调用才能进行消息的回调  必须要设置
    connectionFactory.setPublisherConfirms(rabbitConfig.getPublisherConfirms());
    return connectionFactory;
  }

  /**
   * 创建模板
   * <p>
   * 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置
   * </p>
   *
   * @return
   */
  @Bean
  @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  public RabbitTemplate rabbitTemplate() {
    RabbitTemplate template = new RabbitTemplate(connectionFactory());
    return template;
  }
}
