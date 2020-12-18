package com.hgt.conf;

import com.hgt.intercept.ResponseResultInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 第三步：注册拦截器，拦截所有请求
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //  /** 拦截所有请求，处理返回值
        registry.addInterceptor(new ResponseResultInterceptor()).addPathPatterns("/**");
        /*拦截处理登录业务*/
        //registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
    }
}
