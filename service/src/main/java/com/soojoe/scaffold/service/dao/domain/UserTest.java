package com.soojoe.scaffold.service.dao.domain;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户测试类
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/28 7:31
 */
@Data
@NoArgsConstructor
public class UserTest {

  /**
   * 自增id
   */
  private Long id;

  /**
   * 用户名
   */
  private String name;

  /**
   * 访问次数
   */
  private Long visitNum;

  /**
   * 创建时间
   */
  private Date gmtCreate;

  /**
   * 修改时间
   */
  private Date gmtModified;


  public UserTest(String name, Long visitNum) {
    this.name = name;
    this.visitNum = visitNum;
  }
}
