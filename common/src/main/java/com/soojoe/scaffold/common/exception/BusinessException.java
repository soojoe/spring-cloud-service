package com.soojoe.scaffold.common.exception;

import com.soojoe.common.constants.ResponseActionConstants;
import com.soojoe.common.constants.ResponseCodeConstants;
import com.soojoe.common.exception.AbstractCommonException;
import com.soojoe.course.common.constants.ExceptionCodeConstants;

/**
 * 业务自定义异常类
 *
 * <p>
 *   实现自定义的业务异常必须集成common中的AbstactCommonException异常,
 *   另外，不要用异常控制业务流程，会导致系统效率低下。
 * </p>
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/16 09:23
 */
public class BusinessException extends AbstractCommonException{

  public BusinessException(){
    this(ResponseActionConstants.ALERT);
  }

  public BusinessException(int action){
    code = ResponseCodeConstants.ACCESS_DENIED;
    this.action = action;
  }

  public BusinessException(String message){
    this(ResponseActionConstants.ALERT, message);
  }

  public BusinessException(int action, String message) {
    super(message);
    code = ExceptionCodeConstants.BUSINESS_ERROR;
    this.action = action;
    this.message = message;
  }
}
