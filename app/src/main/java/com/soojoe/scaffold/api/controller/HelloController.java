package com.soojoe.scaffold.api.controller;

import com.soojoe.scaffold.api.share.HelloApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * hello控制器
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/28 7:31
 */
@RequestMapping("/hello")
@RestController
public class HelloController {

  @Autowired
  private HelloApi helloApi;


  @GetMapping("/currentTime")
  public String getCurrentTime(@RequestParam String name) {
    return helloApi.getCurrentTime(name);
  }


  @PostMapping("/register")
  public String registerUser(@RequestParam String name) {
    return helloApi.registerUser(name);
  }

}
