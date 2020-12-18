package com.hgt.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hgt.annotation.ResponseResult;
import com.hgt.pojo.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
/*第四步：根据标记，重新封装返回体*/
/*重新封装返回体*/
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {
    /*标记*/
    public static final String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";

    /*请求 是否包含了 包装注解，标记；不包含直接返回，不需要写返回体
    * 此方法返回true时，才会执行beforeBodyWrite()对返回体封装*/
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        //判断请求是否包含包装标记
        ResponseResult responseResultAnn = (ResponseResult) request.getAttribute(RESPONSE_RESULT_ANN);

        return responseResultAnn == null ? false : true;
    }

    /*对返回体封装*/
    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        System.out.println("正在重写返回体...");

        // String类型不能直接包装，所以要进行些特别的处理
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            // 将数据包装在Result里后，再转换为json字符串响应给前端
            try {
                return objectMapper.writeValueAsString(Result.success(body));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }

        //防止重复封装
        if(body instanceof Result){
            return body;
        }
        return Result.success(body);
    }
}
