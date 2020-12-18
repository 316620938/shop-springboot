package com.hgt.intercept;

import com.hgt.annotation.ResponseResult;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/*第三步：定义拦截器
    使用拦截器对所有请求对所有请求拦截，将所有使用@ResponseResult注解的类和方法做统一处理*/
@Component
public class ResponseResultInterceptor implements HandlerInterceptor {
    //标记
    public static final String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";

    /*拦截请求，取所有使用@ResponseResult的类或方法添加标记*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();

            if (clazz.isAnnotationPresent(ResponseResult.class)) {
                request.setAttribute(RESPONSE_RESULT_ANN, clazz.getAnnotation(ResponseResult.class));
            } else if (method.isAnnotationPresent(ResponseResult.class)) {
                request.setAttribute(RESPONSE_RESULT_ANN, method.getAnnotation(ResponseResult.class));
            }
        }
        return true;
    }
}
