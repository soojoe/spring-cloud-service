package com.soojoe.scaffold.config;

import com.soojoe.scaffold.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 课程服务配置类
 *
 * @author suzhou
 * @version 1.0
 * @date 2019/04/28 7:31
 */
@Configuration
@ImportResource(value = "classpath:/dubbo-provider.xml")
public class ApplicationConfig implements WebMvcConfigurer {

  @Autowired
  private LogInterceptor logInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(logInterceptor)
        .addPathPatterns("/**/*")
        .excludePathPatterns("/health/check")
        .order(0);
  }
}
