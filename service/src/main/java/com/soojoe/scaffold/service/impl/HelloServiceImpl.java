package com.soojoe.scaffold.service.impl;

import com.soojoe.scaffold.service.HelloService;
import com.soojoe.scaffold.service.dao.UserTestMapper;
import com.soojoe.scaffold.service.helper.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Hello业务层实现
 *
 * @author ginger
 * @create 2019-04-12 21:06
 */

@Slf4j
@Service
public class HelloServiceImpl implements HelloService {

  @Autowired
  private UserTestMapper userTestMapper;

  @Autowired
  private RedisHelper redisHelper;

  /**
   * 获取用户访问信息
   *
   * @param name 用户名
   * @return 访问信息
   */
  @Override
  public String getCurrentTime(String name) {
    String key = "uname:" + name + ":num";
    String value = redisHelper.get(key);
    if (null == value) {
      int count = userTestMapper.countOfName(name);
      if (count == 0) {
        return name + "，您尚未注册！";
      }
      redisHelper.putObject(key, 1, 60);
      return "Hello, " + name + ", current time is " + LocalDateTime.now().toString();
    }
    if (Integer.valueOf(value) > 2) {
      return name + "，您访问过于频繁，请稍后重试！";
    }
    redisHelper.incr(key, 1L);
    return "Hello, " + name + ", current time is " + LocalDateTime.now().toString();
  }


  /**
   * 注册用户
   *
   * @param name 用户名
   * @return 注册结果
   */
  @Override
  public String registerUser(String name) {
    int count = userTestMapper.countOfName(name);
    if (count > 0) {
      return name + "，您已经注册过！";
    }
    if (userTestMapper.insert(name) > 0) {
      return name + "，注册成功！";
    }
    return name + "，注册失败！";
  }

}
