package com.soojoe.scaffold.api.impl;

import com.soojoe.scaffold.api.share.HelloApi;
import com.soojoe.scaffold.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * hello接口实现
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/28 7:31
 */
@Slf4j
@Service
public class HelloApiImpl implements HelloApi {

  @Autowired
  private HelloService helloService;

  @Override
  public String getCurrentTime(String name) {
    if ("Test".equalsIgnoreCase(name)) {
      throw new RuntimeException("非法的用户名");
    }
    return helloService.getCurrentTime(name);
  }

  @Override
  public String registerUser(String name) {
    if ("Test".equalsIgnoreCase(name)) {
      throw new RuntimeException("非法的用户名");
    }
    return helloService.registerUser(name);
  }
}
