package com.soojoe.scaffold.service.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 用户测试类mapper
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/28 7:31
 */
@Repository
public interface UserTestMapper {

  /**
   * 插入用户
   *
   * @param name 用户名称
   * @return 插入结果
   */
  @Insert("INSERT user_test(name) VALUES(#{name})")
  int insert(@Param("name") String name);

  /**
   * 查询用户数
   *
   * @param name 用户名称
   * @return 查询到的个数
   */
  @Select("SELECT COUNT(*) FROM user_test WHERE name = #{name}")
  int countOfName(@Param("name") String name);

}
