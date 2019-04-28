package com.soojoe.scaffold.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/28 7:31
 */
@Slf4j
@RestController
@RequestMapping(value = "/health")
public class HealthController {

  @RequestMapping("/check")
  public void check(HttpServletResponse httpResponse) {
    httpResponse.setStatus(HttpStatus.SC_OK);
    httpResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    try (PrintWriter pw = httpResponse.getWriter()) {
      pw.write("{\"status\":\"ok\"}");
    } catch (IOException ignored) {
    }
  }

}
