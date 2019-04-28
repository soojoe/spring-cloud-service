package com.soojoe.scaffold.mq;

import com.soojoe.scaffold.CourseApplicationTest;
import com.soojoe.scaffold.service.mq.Producer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 生产者测试
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/24 11:43
 */
public class ProducerTest extends CourseApplicationTest {

  @Autowired
  private Producer producer;

  @Test
  public void test_send(){
      producer.send("hello rabbit");
  }
}
