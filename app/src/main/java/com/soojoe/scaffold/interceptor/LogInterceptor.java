package com.soojoe.scaffold.interceptor;

import com.soojoe.scaffold.common.constants.LogConstant;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 日志拦截器
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/28 7:31
 */
@Slf4j
@Component
public class LogInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String reqId = request.getParameter("reqId");
    if (StringUtils.isBlank(reqId)) {
      reqId = UUID.randomUUID().toString().replace("-", "");
    }
    request.setAttribute(LogConstant.TRACE_REQ_ID, reqId);
    request.setAttribute(LogConstant.TRACE_START_TIME, System.currentTimeMillis());
    return true;
  }


  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {

  }


  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
    String reqId = (String) request.getAttribute(LogConstant.TRACE_REQ_ID);
    long startTime = (long) request.getAttribute(LogConstant.TRACE_START_TIME);
    long cost = System.currentTimeMillis() - startTime;
    log.info("trace result, reqId={}, requestUri={}, queryString={}, cost={}ms", reqId,
        request.getRequestURI(), request.getQueryString(), cost);
  }
}
