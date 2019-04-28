package com.soojoe.scaffold;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 服务启动类
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/28 7:31
 */
@SpringBootApplication
//@EnableDubboConfiguration
public class ScaffoldApplication {

  public static void main(String[] args) {
    SpringApplication.run(ScaffoldApplication.class, args);
  }
}
