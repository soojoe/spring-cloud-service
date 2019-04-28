package com.soojoe.scaffold.exception;

import com.soojoe.common.constants.ResponseActionConstants;
import com.soojoe.common.constants.ResponseCodeConstants;
import com.soojoe.common.dto.ResponseDTO;
import com.soojoe.common.exception.AbstractCommonException;
import com.soojoe.course.common.constants.LogConstant;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局异常处理
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/28 7:31
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = Exception.class)
  @ResponseStatus
  @ResponseBody
  public ResponseDTO commonErrorHandler(HttpServletRequest req, Exception e) {
    String reqId = req.getAttribute(LogConstant.TRACE_REQ_ID).toString();
    String reqUri = req.getRequestURI();
    log.error("handle error, reqId={}, reqUri={}", reqId, reqUri, e);
    if (e instanceof AbstractCommonException) {
      AbstractCommonException exception = (AbstractCommonException) e;
      return new ResponseDTO(exception.getCode(), exception.getAction(), exception.getMessage(),
          reqId);
    }

    return new ResponseDTO(ResponseCodeConstants.SERVER_ERROR, ResponseActionConstants.ALERT,
        "服务器出现错误,请稍后重试", reqId);
  }

}
