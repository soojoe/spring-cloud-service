package com.soojoe.scaffold.api.share;

/**
 * hello接口
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/28 7:31
 */
public interface HelloApi {

  /**
   * 获取用户的访问信息
   *
   * @param name 用户名
   * @return 访问信息
   */
  String getCurrentTime(String name);

  /**
   * 注册用户
   *
   * @param name 用户名
   * @return 注册结果
   */
  String registerUser(String name);
}