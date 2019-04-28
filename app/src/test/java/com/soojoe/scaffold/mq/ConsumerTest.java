package com.soojoe.scaffold.mq;

import com.soojoe.scaffold.CourseApplicationTest;
import com.soojoe.scaffold.service.mq.Consumer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 消费者测试
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/24 11:43
 */
public class ConsumerTest extends CourseApplicationTest {

  @Autowired
  private Consumer consumer;

  @Test
  public void test_process(){

  }

}
