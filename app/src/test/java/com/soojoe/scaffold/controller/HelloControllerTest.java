package com.soojoe.scaffold.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * hello控制器
 *
 * @author suzhou
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloControllerTest {

  @Test
  public void hello() {
    log.info("This is test.");
  }

}
